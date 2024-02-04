package ma.fstm.ilisi.caisse.metier.service;

public class AuthService {
    private static int cmpt;
    public String checkAuth(String username, String password) {
        if (username.equals("ismail") && password.equals("123")) {
            cmpt = 0;
            return "SUCCESS";
        }
        cmpt++;
        if (cmpt < 3)
            return Integer.toString(3 - cmpt);
        return "FAILED";
    }
}
