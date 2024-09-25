package project.task.Helpers;

public class Autorizacion {
    public enum Rols {
        ADMIN,
        USER

    }

    public static final Rols ROL_POR_DEFECTO = Rols.USER;

    public static Rols getRolPorDefecto() {
        return ROL_POR_DEFECTO;
    }
}
