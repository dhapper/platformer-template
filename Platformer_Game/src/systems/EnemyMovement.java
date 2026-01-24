package systems;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import entities.Entity;
import entities.LivingEntity;
import enums.Facing;
import utilz.HelperMethods;

public class EnemyMovement {

    private Movement movement;

    public EnemyMovement(Movement movement) {
        this.movement = movement;
    }

    // --------------------------------
    // VERTICAL MOVEMENT (GROUND)
    // --------------------------------
    private boolean applyVerticalMovement(Rectangle2D.Float hitbox, ArrayList<Entity> entities) {
        float newY = hitbox.y;

        Rectangle2D.Float futureY = new Rectangle2D.Float(
                hitbox.x,
                hitbox.y + movement.getPhysics().getVelY(),
                hitbox.width,
                hitbox.height
        );

        if (HelperMethods.CanMoveHere(futureY, entities, movement.getLivingEntity())) {
            newY += movement.getPhysics().getVelY();
        } else {
            movement.getPhysics().setVelY(0);
            movement.getPhysics().setOnGround(true);
        }

        hitbox.y = newY;

        // Stop further movement if still airborne or hurt
        return !movement.getPhysics().isOnGround()
                || movement.getLivingEntity().isHurt();
    }

    // --------------------------------
    // HORIZONTAL: STANDARD WALKING
    // --------------------------------
    private float defaultHorizontal(Rectangle2D.Float hitbox, ArrayList<Entity> entities) {
        float newX = hitbox.x;
        float speed = movement.getSpeed();
        LivingEntity entity = movement.getLivingEntity();

        Rectangle2D.Float future;

        if (entity.getFacing() == Facing.LEFT) {
            future = new Rectangle2D.Float(
                    hitbox.x - speed, hitbox.y, hitbox.width, hitbox.height
            );
            if (HelperMethods.CanMoveHere(future, entities, entity)) {
                newX -= speed;
            }
        } else {
            future = new Rectangle2D.Float(
                    hitbox.x + speed, hitbox.y, hitbox.width, hitbox.height
            );
            if (HelperMethods.CanMoveHere(future, entities, entity)) {
                newX += speed;
            }
        }

        boolean turn =
                HelperMethods.ShouldTurn(hitbox, entities, entity)
                || !HelperMethods.CanMoveHere(
                        future,
                        entity.getLevelManager().getEnemies(),
                        entity
                )
                || !HelperMethods.CanMoveHere(
                        future,
                        entity.getLevelManager().getTiles(),
                        entity
                );

        if (turn) {
            entity.setFacing(
                    entity.getFacing() == Facing.LEFT
                            ? Facing.RIGHT
                            : Facing.LEFT
            );
        }

        return newX;
    }

    // --------------------------------
    // HORIZONTAL: FOLLOW PLAYER
    // --------------------------------
    private float followHorizontal(Rectangle2D.Float hitbox, ArrayList<Entity> entities) {
        float newX = hitbox.x;
        LivingEntity entity = movement.getLivingEntity();

        if (!entity.isPlayerInVision()) {
            return newX;
        }

        Rectangle2D.Float playerHitbox = entity.getPlayer().getHitbox();

        float playerCenter = playerHitbox.x + playerHitbox.width / 2f;
        float entityCenter = hitbox.x + hitbox.width / 2f;

        boolean runRight = playerCenter > entityCenter;
        float speed = movement.getSpeed();

        Rectangle2D.Float future = new Rectangle2D.Float(
                runRight ? hitbox.x + speed : hitbox.x - speed,
                hitbox.y,
                hitbox.width,
                hitbox.height
        );

        if (HelperMethods.CanMoveHere(future, entities, entity)) {
            newX += runRight ? speed : -speed;
        }

        entity.setFacing(runRight ? Facing.RIGHT : Facing.LEFT);

        return newX;
    }

    // --------------------------------
    // PUBLIC BEHAVIORS
    // --------------------------------
    public void standardWalking() {
        Rectangle2D.Float hitbox = movement.getLivingEntity().getHitbox();
        ArrayList<Entity> entities =
                movement.getLivingEntity().getLevelManager().getEntities();

        if (applyVerticalMovement(hitbox, entities)) {
            return;
        }

        hitbox.x = defaultHorizontal(hitbox, entities);
    }

    public void followPlayer() {
        Rectangle2D.Float hitbox = movement.getLivingEntity().getHitbox();
        ArrayList<Entity> entities =
                movement.getLivingEntity().getLevelManager().getEntities();

        if (applyVerticalMovement(hitbox, entities)) {
            return;
        }

        hitbox.x = followHorizontal(hitbox, entities);
    }
}
