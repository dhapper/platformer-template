package enums;

public enum BgColour {
	
//	BLUE,
//	BROWN,
//	GRAY,
//	GREEN,
//	PINK,
//	PURPLE,
//	YELLOW
	
    BLUE("Blue.png"),
    BROWN("Brown.png"),
    GRAY("Gray.png"),
    GREEN("Green.png"),
    PINK("Pink.png"),
    PURPLE("Purple.png"),
    YELLOW("Yellow.png");

    public final String file;

    BgColour(String file) {
        this.file = file;
    }


}
