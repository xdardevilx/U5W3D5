package valerio.U5W3D5.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(long id) {
        super("La tua ricerca non è andata a buon fine! L' id " + id + " non è stato trovato!");
    }
}
