package gr.atc.polivisu.model;

public class JWTSecret {

    private static final String SECRET = "23745288qfu3trd5487463u4degh";
    
    private JWTSecret() {
    }

    public static String getSecret() {
        return SECRET;
    }
}
