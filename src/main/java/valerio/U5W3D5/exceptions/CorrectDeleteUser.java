package valerio.U5W3D5.exceptions;

public class CorrectDeleteUser extends RuntimeException {
    public CorrectDeleteUser(long message) {
        super("User eliminato correttamente");
    }

}
