package valerio.U5W3D5.exceptions;

public class CorrectDeletEvent extends RuntimeException {
    public CorrectDeletEvent(String message) {
        super("evento eliminato correttamente");
    }
}
