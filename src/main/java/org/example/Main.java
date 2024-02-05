package org.example;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Main {
    private static int tiempoRestante;
    static final String DB_URL = "jdbc:mysql://sql.freedb.tech:3306/freedb_el_concursillo";
    static final String USER = "freedb_elconcursilloadmin";
    static final String PASS = "cb!J#KjBfZr&GR9";

    public static void main(String[] args) {
        System.out.println();System.out.println();System.out.println();
        System.out.println(YELLOW_BRIGHT+" #######   ###                ####                                                           ##      ###      ###");
        System.out.println("  ##   #    ##               ##  ##                                                                   ##       ##");
        System.out.println("  ## #      ##              ##        ####    #####     ####    ##  ##   ######    #####    ###       ##       ##      ####");
        System.out.println("  ####      ##              ##       ##  ##   ##  ##   ##  ##   ##  ##    ##  ##  ##         ##       ##       ##     ##  ##");
        System.out.println("  ## #      ##              ##       ##  ##   ##  ##   ##       ##  ##    ##       #####     ##       ##       ##     ##  ##");
        System.out.println("  ##   #    ##               ##  ##  ##  ##   ##  ##   ##  ##   ##  ##    ##           ##    ##       ##       ##     ##  ##");
        System.out.println(" #######   ####               ####    ####    ##  ##    ####     ######  ####     ######    ####     ####     ####     ####");
        System.out.println();System.out.println();
        mostrarBarraDeCarga(100);
        System.out.println();
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stat = conn.createStatement();
            Scanner sc = new Scanner(System.in);
            String lletra;
            String opcio;
            do {
                String nicknameSesion = null;
                System.out.println("|---------------------------------------------------------------------------------------------------------------------------|");
                System.out.println("|    a. INICIAR SESSIÓ                                                                                                      |");
                System.out.println("|    b. REGISTRARSE                                                                                                         |");
                System.out.println("|    c. SORTIR                                                                                                              |");
                System.out.println("|---------------------------------------------------------------------------------------------------------------------------|");
                System.out.print("|    Escolleix una de les opcions per a continuar: ");
                opcio = sc.nextLine();
                llimpiarpantalla();

                switch (opcio.toLowerCase()) {
                    case "a":
                        while (nicknameSesion == null) {
                            nicknameSesion = iniciarSessio(conn, stat, sc);
                        }
                        // ---------------------------------------------------------------------------------------------//
                        do {
                            System.out.println("|---------------------------------------------------------------------------------------------------------------------------|");
                            System.out.println("|    a. Jugar Partida                                                                                                       |");
                            System.out.println("|    b. Veure Estadístiques                                                                                                 |");
                            System.out.println("|    c. Tanca sessió                                                                                                        |");
                            boolean esAdministrador = obtenirEstatAdministrador(conn, stat, sc, nicknameSesion);
                            if (esAdministrador) {
                                System.out.println("|    d. Administrar Preguntas y Respuestas                                                                                  |");
                            }
                            if (nicknameSesion.equals("admin")){
                                System.out.println("|    e. Administrar Usuaris                                                                                                 |");
                            }
                            System.out.println("|---------------------------------------------------------------------------------------------------------------------------|");
                            System.out.print("|    Escolleix una de les opcions per a continuar: ");
                            lletra = sc.nextLine();
                            llimpiarpantalla();
                            if (lletra.equals("a")) {
                                System.out.println("|---------------------------------------------------------------------------------------------------------------------------|");
                                System.out.println("|    a. Normal                                                                                                              |");
                                System.out.println("|    b. Competitiu                                                                                                          |");
                                System.out.println("|    c. Survival                                                                                                            |");
                                System.out.println("|    d. Per categoria                                                                                                       |");
                                System.out.println("|    e. Enrere                                                                                                              |");
                                System.out.println("|---------------------------------------------------------------------------------------------------------------------------|");
                                System.out.print("|    Escolleix una de les opcions per a continuar: ");
                                String lletrap = sc.nextLine();
                                llimpiarpantalla();
                                if (lletrap.equals("a")) {
                                    iniciarPartidaNormal(conn, nicknameSesion, sc);
                                } else if (lletrap.equals("b")) {
                                    iniciarPartidaCompetitiva(conn, nicknameSesion, sc);
                                } else if (lletrap.equals("c")) {
                                    iniciarPartidaSurvival(conn, nicknameSesion, sc);
                                } else if (lletrap.equals("d")) {
                                    iniciarPartidaCategoria(conn,nicknameSesion,sc);
                                }
                            } else if (lletra.equals("b")) {
                                System.out.println("|---------------------------------------------------------------------------------------------------------------------------|");
                                System.out.println("|    a. Estadístiques globals                                                                                               |");
                                System.out.println("|    b. Estadístiques d'usuari                                                                                              |");
                                System.out.println("|    c. Enrere                                                                                                              |");
                                System.out.println("|---------------------------------------------------------------------------------------------------------------------------|");
                                System.out.print("|    Escolleix una de les opcions per a continuar: ");
                                String lletrae = sc.nextLine();
                                llimpiarpantalla();
                                if (lletrae.equals("a")) {
                                    estadisticaGlobal(conn, stat, sc);
                                } else if (lletrae.equals("b")) {
                                    estadistiquesUsuari(conn, stat, sc, nicknameSesion);
                                } else {

                                }
                            } else if (lletra.equals("c")) {
                                tancarSessio(conn, nicknameSesion);
                                nicknameSesion = null;
                                System.out.println("|---------------------------------------------------------------------------------------------------------------------------|");
                                System.out.println("|    Sessió tancada amb èxit.                                                                                               |");
                                System.out.println("|---------------------------------------------------------------------------------------------------------------------------|");
                            } else if (lletra.equals("d") && esAdministrador) {
                                String lletraadmin = " ";
                                do {
                                    System.out.println("|---------------------------------------------------------------------------------------------------------------------------|");
                                    System.out.println("|    a. Veure totes les preguntes                                                                                           |");
                                    System.out.println("|    b. Editar una pregunta                                                                                                 |");
                                    System.out.println("|    c. Esborrar una pregunta                                                                                               |");
                                    System.out.println("|    d. Fer una nova pregunta                                                                                               |");
                                    System.out.println("|    e. Enrere                                                                                                              |");
                                    System.out.println("|---------------------------------------------------------------------------------------------------------------------------|");
                                    System.out.print("|    Escolleix una de les opcions per a continuar: ");
                                    lletraadmin = sc.nextLine();
                                    llimpiarpantalla();
                                    if (lletraadmin.equals("a")) {
                                        veurePreguntes(conn, stat, sc);
                                    } else if (lletraadmin.equals("b")) {
                                        editarPreguntes(conn, stat, sc);
                                    } else if (lletraadmin.equals("c")) {
                                        eliminarPregunta(conn, sc);
                                    } else if (lletraadmin.equals("d")) {
                                        ferNovaPregunta(conn, sc);
                                    } else{
                                        System.out.println("|---------------------------------------------------------------------------------------------------------------------------|");
                                        System.out.println("|    Opció incorrecta, torna a probar!                                                                                      |");
                                        System.out.println("|---------------------------------------------------------------------------------------------------------------------------|");
                                    }

                                } while (!lletraadmin.equals("e"));

                            } else if (lletra.equals("e")) {
                                String lletrau = " ";
                                do {
                                    System.out.println("|---------------------------------------------------------------------------------------------------------------------------|");
                                    System.out.println("|    a. Veure tots els usuaris                                                                                              |");
                                    System.out.println("|    b. Donar o treure permisos                                                                                             |");
                                    System.out.println("|    c. Esborrar usuari                                                                                                     |");
                                    System.out.println("|    e. Enrere                                                                                                              |");
                                    System.out.println("|---------------------------------------------------------------------------------------------------------------------------|");
                                    System.out.print("|    Escolleix una de les opcions per a continuar: ");
                                    lletrau=sc.nextLine();
                                    llimpiarpantalla();
                                    if (lletrau.equals("a")) {
                                        veureUsuaris(conn, stat);
                                    } else if(lletrau.equals("b")){
                                        editarPermisos(conn,stat,sc);
                                    } else if (lletrau.equals("c")) {
                                        esborrarUsuari(conn,stat,sc);
                                    } else{
                                        System.out.println("|---------------------------------------------------------------------------------------------------------------------------|");
                                        System.out.println("|    Opcio incorrecta, torna a probar!                                                                                      |");
                                        System.out.println("|---------------------------------------------------------------------------------------------------------------------------|");
                                    }
                                } while (!lletrau.equals("e"));
                            }
                            else {
                                System.out.println("|---------------------------------------------------------------------------------------------------------------------------|");
                                System.out.println("|    Opcio incorrecta, torna a probar!                                                                                      |");
                                System.out.println("|---------------------------------------------------------------------------------------------------------------------------|");
                            }
                        } while (!lletra.equals("c"));

                        //--------------------------------------------------------------------------------------//
                        break;
                    case "b":
                        registrarUsuari(conn, stat, sc);
                        break;
                    case "c":
                        tancarSessio(conn, nicknameSesion);
                        nicknameSesion = null;
                        System.out.println("Sessió tancada amb èxit.");
                        break;
                    case "d":
                        System.out.println("Gràcies per jugar. Adéu!");
                        sc.close();
                        conn.close();
                        System.exit(0);
                    default:
                        System.out.println("|---------------------------------------------------------------------------------------------------------------------------|");
                        System.out.println("|    Opcio incorrecta, torna a probar!                                                                                      |");
                        System.out.println("|---------------------------------------------------------------------------------------------------------------------------|");
                        break;
                }
            } while (!opcio.equals("c"));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        llimpiarpantalla();
    }
    public static String iniciarSessio(Connection conn, Statement stat, Scanner sc) throws SQLException {
        System.out.print("|    Introdueix el teu nickname: ");
        String nickname = sc.nextLine();
        System.out.print("|    Introdueix la teva contrasenya: ");
        String contrasenya = sc.nextLine();

        String query = "SELECT * FROM usuaris WHERE nickname = ? AND contrasenya = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, nickname);
            pstmt.setString(2, contrasenya);

            try (ResultSet resultSet = pstmt.executeQuery()) {
                if (resultSet.next()) {
                    actualitzarStatus(conn, nickname, true);
                    return nickname;

                }
                else {
                    System.out.println("|    Nickname o contrasenya incorrectes. Torna a intentar-ho.");
                    return null;
                }
            }
        }

    }

    public static void tancarSessio(Connection conn, String nickname) throws SQLException {
        if (nickname != null) {
            actualitzarStatus(conn, nickname, false);
        }
    }

    public static void registrarUsuari(Connection conn, Statement stat, Scanner sc) throws SQLException {
        String nickname = "";
        boolean existeix = false;
        while(!existeix) {
            System.out.print("|    Introdueix el teu nickname: ");
            nickname = sc.nextLine();
            String queryNicknameExistent = "SELECT COUNT(*) AS num_usuaris FROM usuaris WHERE nickname = ?";
            try (PreparedStatement pstmtNickname = conn.prepareStatement(queryNicknameExistent)) {
                pstmtNickname.setString(1, nickname);
                try (ResultSet rs = pstmtNickname.executeQuery()) {
                    if (rs.next()) {
                        int numUsuaris = rs.getInt("num_usuaris");
                        if (numUsuaris > 0) {
                            System.out.println("|     Ja existeix un usuari amb aquest nickname. Torna a intentar-ho amb un altre nickname.");

                        } else {
                            existeix = true;
                        }
                    }
                }
            }
        }
        System.out.print("|    Introdueix el teu nom: ");
        String nom = sc.nextLine();
        System.out.print("|    Introdueix el teu cognom: ");
        String cognom = sc.nextLine();
        System.out.print("|    Introdueix la teva contrasenya: ");
        String contrasenya = sc.nextLine();
        System.out.print("|    Introdueix el teu país: ");
        String pais = sc.nextLine();
        System.out.print("|    Introdueix la teva edat: ");
        int edat = Integer.parseInt(sc.nextLine());
        System.out.print("|    Introdueix el teu correu electrònic: ");
        String correuElectronic = sc.nextLine();
        try {
            conn.setAutoCommit(false);
            int nouIdUsuari = obtenirNouId(conn);
            String queryInsertUsuari = "INSERT INTO usuaris (ID_usuari, nickname, nom, cognom, contrasenya, pais, edat, data_registre, correu_electronic, Status) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, NOW(), ?, false)";
            try (PreparedStatement pstmtUsuari = conn.prepareStatement(queryInsertUsuari)) {
                pstmtUsuari.setInt(1, nouIdUsuari);
                pstmtUsuari.setString(2, nickname);
                pstmtUsuari.setString(3, nom);
                pstmtUsuari.setString(4, cognom);
                pstmtUsuari.setString(5, contrasenya);
                pstmtUsuari.setString(6, pais);
                pstmtUsuari.setInt(7, edat);
                pstmtUsuari.setString(8, correuElectronic);
                int filesAfectadesUsuari = pstmtUsuari.executeUpdate();
                if (filesAfectadesUsuari > 0) {
                    String queryInsertEstadistiques = "INSERT INTO estadistiques (ID_usuari, victories, partides_jugades, preguntes_correctes, punts_competitius, punts_normals) " +
                            "VALUES (?, 0, 0, 0, 0, 0)";
                    try (PreparedStatement pstmtEstadistiques = conn.prepareStatement(queryInsertEstadistiques)) {
                        pstmtEstadistiques.setInt(1, nouIdUsuari);
                        int filesAfectadesEstadistiques = pstmtEstadistiques.executeUpdate();
                        if (filesAfectadesEstadistiques > 0) {
                        } else {
                            System.out.println("|     Error en la inicialització de les estadístiques per a l'usuari: " + nouIdUsuari);
                        }
                    }
                } else {
                    System.out.println("|     Error en el registre de l'usuari. Torna a intentar-ho.");
                }
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            } finally {
                conn.setAutoCommit(true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static int obtenirNouId(Connection conn) throws SQLException {
        String queryMaxId = "SELECT MAX(ID_usuari) FROM usuaris";
        try (Statement stmt = conn.createStatement();
             ResultSet resultSet = stmt.executeQuery(queryMaxId)) {
            int nuevoId = 1;
            if (resultSet.next()) {
                nuevoId = resultSet.getInt(1) + 1;
            }
            return nuevoId;
        }
    }

    public static boolean obtenirEstatAdministrador(Connection conn, Statement stat, Scanner sc, String nickname) throws SQLException {
        String consulta = "SELECT admin FROM usuaris WHERE nickname = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(consulta)) {
            pstmt.setString(1, nickname);
            try (ResultSet resultSet = pstmt.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getBoolean("admin");
                }
            }
        }
        return false;
    }

    public static void actualitzarStatus(Connection conn, String nickname, boolean nouStatus) throws SQLException {
        String query = "UPDATE usuaris SET status = ? WHERE nickname = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setBoolean(1, nouStatus);
            pstmt.setString(2, nickname);
            pstmt.executeUpdate();
        }
    }

    private static void estadisticaGlobal(Connection conn, Statement stat, Scanner sc) throws SQLException {
        String sql = "SELECT u.nickname, e.ID_usuari, e.victories, e.partides_jugades, e.preguntes_correctes, e.punts_competitius, e.punts_normals " +
                "FROM usuaris u INNER JOIN estadistiques e ON u.ID_usuari = e.ID_usuari";
        ResultSet rs = stat.executeQuery(sql);
        while (rs.next()) {
            String nickname = rs.getString("nickname");
            int id_usuari = rs.getInt("ID_usuari");
            int victories = rs.getInt("victories");
            int partides_jugades = rs.getInt("partides_jugades");
            int preguntes_correctes = rs.getInt("preguntes_correctes");
            int punts_competitius = rs.getInt("punts_competitius");
            int punts_normals = rs.getInt("punts_normals");

            System.out.println("|     nickname: " + nickname);
            System.out.println("|     ID: " + id_usuari);
            System.out.println("|     victories: " + victories);
            System.out.println("|     partides_jugades: " + partides_jugades);
            System.out.println("|     preguntes_correctes: " + preguntes_correctes);
            System.out.println("|     punts_competitius: " + punts_competitius);
            System.out.println("|     punts_normals: " + punts_normals);
            System.out.println("|---------------------------------------------------------------------------------------------------------------------------|");
        }
    }

    private static void estadistiquesUsuari(Connection conn, Statement stat, Scanner sc, String nickname) throws SQLException {
        String sql = "SELECT victories, partides_jugades, preguntes_correctes, punts_competitius, punts_normals " +
                "FROM estadistiques WHERE id_usuari = (SELECT ID_usuari FROM usuaris WHERE nickname = ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nickname);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int victories = rs.getInt("victories");
                int partides_jugades = rs.getInt("partides_jugades");
                int preguntes_correctes = rs.getInt("preguntes_correctes");
                int punts_competitius = rs.getInt("punts_competitius");
                int punts_normals = rs.getInt("punts_normals");

                System.out.println("|     Estadístiques de l'usuari " + nickname + ":");
                System.out.println("|     Victòries: " + victories);
                System.out.println("|     Partides jugades: " + partides_jugades);
                System.out.println("|     Preguntes correctes: " + preguntes_correctes);
                System.out.println("|     Punts competitius: " + punts_competitius);
                System.out.println("|     Punts normals: " + punts_normals);
            } else {
                System.out.println("|     No s'han trobat estadístiques per a l'usuari " + nickname);
            }
        }
    }

    private static void veurePreguntes(Connection conn, Statement stat, Scanner sc) throws SQLException {
        System.out.println("|     Llista de Preguntes:");
        System.out.println("|---------------------------------------------------------------------------------------------------------------------------|");

        String query = "SELECT id_pregunta, pregunta FROM preguntes";
        try (PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet resultSet = pstmt.executeQuery()) {

            while (resultSet.next()) {
                int idPregunta = resultSet.getInt("id_pregunta");
                String enunciat = resultSet.getString("pregunta");

                System.out.println("|     ID Pregunta: " + idPregunta);
                System.out.println("|     Enunciat: " + enunciat);
                System.out.println("|---------------------------------------------------------------------------------------------------------------------------|");
            }
        }

        System.out.println("|     Selecciona l'ID de la pregunta per a veure les respostes (0 per a sortir):");

        int idSeleccionado = Integer.parseInt(sc.nextLine());

        if (idSeleccionado > 0) {
            veureRespostes(conn, idSeleccionado, sc);
        }
    }

    private static void veureRespostes(Connection conn, int idPregunta, Scanner sc) throws SQLException {
        String query = "SELECT id_resposta, resposta FROM respostes WHERE id_pregunta = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, idPregunta);

            try (ResultSet resultSet = pstmt.executeQuery()) {
                System.out.println("|     Respostes a la pregunta " + idPregunta + ":");
                System.out.println("|---------------------------------------------------------------------------------------------------------------------------|");

                while (resultSet.next()) {
                    int idResposta = resultSet.getInt("id_resposta");
                    String resposta = resultSet.getString("resposta");

                    System.out.println("|     ID Resposta: " + idResposta);
                    System.out.println("|     Resposta: " + resposta);
                    System.out.println("|---------------------------------------------------------------------------------------------------------------------------|");
                }
            }
        }
    }

    private static void editarPreguntes(Connection conn, Statement stat, Scanner sc) throws SQLException {
        System.out.println("|     Introdueix l'ID de la pregunta que vols editar (0 per a sortir):");
        int idPregunta = Integer.parseInt(sc.nextLine());

        if (idPregunta <= 0) {
            return;
        }

        // Obtener la pregunta actual
        String preguntaQuery = "SELECT pregunta FROM preguntes WHERE id_pregunta = ?";
        try (PreparedStatement preguntaStmt = conn.prepareStatement(preguntaQuery)) {
            preguntaStmt.setInt(1, idPregunta);

            try (ResultSet preguntaResult = preguntaStmt.executeQuery()) {
                if (preguntaResult.next()) {
                    String preguntaActual = preguntaResult.getString("pregunta");
                    System.out.println("|     Pregunta actual: " + preguntaActual);

                    // Mostrar les respostes actuals
                    mostrarRespostes(conn, idPregunta);

                    // Solicitar una nova pregunta
                    System.out.println("|     Introdueix la nova pregunta:");
                    String novaPregunta = sc.nextLine();

                    // Actualitzar la pregunta a la base de dades
                    String updatePreguntaQuery = "UPDATE preguntes SET pregunta = ? WHERE id_pregunta = ?";
                    try (PreparedStatement updatePreguntaStmt = conn.prepareStatement(updatePreguntaQuery)) {
                        updatePreguntaStmt.setString(1, novaPregunta);
                        updatePreguntaStmt.setInt(2, idPregunta);
                        int filasAfectadas = updatePreguntaStmt.executeUpdate();

                        if (filasAfectadas > 0) {
                            System.out.println("|     Pregunta actualitzada amb èxit.");
                        } else {
                            System.out.println("|     No s'ha pogut actualitzar la pregunta.");
                        }
                    }

                    editarRespostes(conn, sc, idPregunta);
                } else {
                    System.out.println("|     No s'ha trobat cap pregunta amb aquest ID.");
                }
            }
        }
    }

    private static void mostrarRespostes(Connection conn, int idPregunta) throws SQLException {
        // Mostrar les respostes actuals
        String respuestasQuery = "SELECT id_resposta, resposta, correcta FROM respostes WHERE id_pregunta = ?";
        try (PreparedStatement respuestasStmt = conn.prepareStatement(respuestasQuery)) {
            respuestasStmt.setInt(1, idPregunta);
            try (ResultSet respuestasResult = respuestasStmt.executeQuery()) {
                while (respuestasResult.next()) {
                    int idRespuesta = respuestasResult.getInt("id_resposta");
                    String respuesta = respuestasResult.getString("resposta");
                    boolean esCorrecta = respuestasResult.getBoolean("correcta");

                    System.out.println("|     Resposta " + idRespuesta + ": " + respuesta + " (Correcta: " + esCorrecta + ")");
                }
            }
        }
    }

    private static void editarRespostes(Connection conn, Scanner sc, int idPregunta) throws SQLException {
        // Obtener les respostes actuals
        String respuestasQuery = "SELECT id_resposta, resposta, correcta FROM respostes WHERE id_pregunta = ?";
        try (PreparedStatement respuestasStmt = conn.prepareStatement(respuestasQuery)) {
            respuestasStmt.setInt(1, idPregunta);

            try (ResultSet respostesResult = respuestasStmt.executeQuery()) {
                while (respostesResult.next()) {
                    int idResposta = respostesResult.getInt("id_resposta");
                    String respuestaActual = respostesResult.getString("resposta");
                    boolean esCorrecta = respostesResult.getBoolean("correcta");

                    System.out.println("|     Resposta actual " + idResposta + ": " + respuestaActual + " (Correcta: " + esCorrecta + ")");

                    // Solicitar la nova resposta
                    System.out.println("|     Introdueix la nova resposta per la resposta " + idResposta + ":");
                    String novaRespuesta = sc.nextLine();

                    // Actualitzar la resposta a la base de dades
                    String updateRespuestaQuery = "UPDATE respostes SET resposta = ? WHERE id_resposta = ?";
                    try (PreparedStatement updateRespuestaStmt = conn.prepareStatement(updateRespuestaQuery)) {
                        updateRespuestaStmt.setString(1, novaRespuesta);
                        updateRespuestaStmt.setInt(2, idResposta);
                        int filasAfectadas = updateRespuestaStmt.executeUpdate();

                        if (filasAfectadas > 0) {
                            System.out.println("|     Resposta actualitzada amb èxit.");
                        } else {
                            System.out.println("|     No s'ha pogut actualitzar la resposta.");
                        }
                    }
                }
            }
        }
    }

    public static void eliminarPregunta(Connection conn, Scanner sc) throws SQLException {
        veurePreguntes(conn, null, sc);

        System.out.println("|     Introdueix l'ID de la pregunta que vols esborrar (0 per a sortir):");
        int idPregunta = Integer.parseInt(sc.nextLine());

        if (idPregunta <= 0) {
            return;
        }

        if (!existeixPregunta(conn, idPregunta)) {
            System.out.println("|     No s'ha trobat cap pregunta amb aquest ID.");
            return;
        }

        // Eliminar las respuestas de la pregunta de la tabla 'respostes'
        eliminarRespostes(conn, idPregunta);

        // Eliminar la pregunta de la tabla 'preguntes'
        String eliminarPreguntaQuery = "DELETE FROM preguntes WHERE id_pregunta = ?";
        try (PreparedStatement eliminarPreguntaStmt = conn.prepareStatement(eliminarPreguntaQuery)) {
            eliminarPreguntaStmt.setInt(1, idPregunta);
            int filasAfectadas = eliminarPreguntaStmt.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("|     Pregunta esborrada amb èxit.");
            } else {
                System.out.println("|     No s'ha pogut esborrar la pregunta.");
            }
        }
    }

    private static boolean existeixPregunta(Connection conn, int idPregunta) throws SQLException {
        String query = "SELECT id_pregunta FROM preguntes WHERE id_pregunta = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, idPregunta);
            try (ResultSet resultSet = pstmt.executeQuery()) {
                return resultSet.next();
            }
        }
    }

    private static void eliminarRespostes(Connection conn, int idPregunta) throws SQLException {
        String eliminarRespostesQuery = "DELETE FROM respostes WHERE id_pregunta = ?";
        try (PreparedStatement eliminarRespostesStmt = conn.prepareStatement(eliminarRespostesQuery)) {
            eliminarRespostesStmt.setInt(1, idPregunta);
            eliminarRespostesStmt.executeUpdate();
        }
    }

    private static void ferNovaPregunta(Connection conn, Scanner sc) throws SQLException {
        System.out.println("|     Introdueix la nova pregunta:");
        String novaPregunta = sc.nextLine();

        // Inserir la nova pregunta a la taula 'preguntes'
        String inserirPreguntaQuery = "INSERT INTO preguntes (pregunta) VALUES (?)";
        try (PreparedStatement inserirPreguntaStmt = conn.prepareStatement(inserirPreguntaQuery, Statement.RETURN_GENERATED_KEYS)) {
            inserirPreguntaStmt.setString(1, novaPregunta);
            int filasAfectades = inserirPreguntaStmt.executeUpdate();

            if (filasAfectades > 0) {
                System.out.println("|     Pregunta afegida amb èxit.");

                // Obtenir l'ID de la nova pregunta generat automàticament
                try (ResultSet generatedKeys = inserirPreguntaStmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int idNovaPregunta = generatedKeys.getInt(1);

                        // Afegir respostes per a la nova pregunta
                        afegirRespostes(conn, sc, idNovaPregunta);
                    }
                }
            } else {
                System.out.println("|     No s'ha pogut afegir la pregunta.");
            }
        }
    }

    private static void afegirRespostes(Connection conn, Scanner sc, int idPregunta) throws SQLException {
        boolean afegirResposta = true;

        while (afegirResposta) {
            System.out.println("|     Introdueix una nova resposta per a la pregunta (o escriu 'fi' per acabar):");
            String novaResposta = sc.nextLine();

            if (!novaResposta.equalsIgnoreCase("fi")) {

                System.out.println("|     La resposta és correcta? (sí/no)");
                boolean esCorrecta = sc.nextLine().equalsIgnoreCase("sí");

                // Inserir la nova resposta a la taula 'respostes'
                String inserirRespostaQuery = "INSERT INTO respostes (id_pregunta, resposta, correcta) VALUES (?, ?, ?)";
                try (PreparedStatement inserirRespostaStmt = conn.prepareStatement(inserirRespostaQuery)) {
                    inserirRespostaStmt.setInt(1, idPregunta);
                    inserirRespostaStmt.setString(2, novaResposta);
                    inserirRespostaStmt.setBoolean(3, esCorrecta);
                    inserirRespostaStmt.executeUpdate();
                }

                System.out.println("|     Resposta afegida amb èxit.");
            } else {
                afegirResposta = false;
            }
        }
    }

    public static void iniciarPartidaNormal(Connection conn, String nicknameSesion, Scanner sc) throws SQLException {
        System.out.println("|---------------------------------------------------------------------------------------------------------------------------|");
        System.out.println("|     Has escollit la modalitat de partida 'Normal'. Hauras d'escollir el numero de preguntes que vols respondre, recorda   |");
        System.out.println("|     que la puntuació obtinguda es sumara a la puntuació normal i no a la competitiva, no tens temps limit i si vols       |");
        System.out.println("|     sortir nomes has de fer click al 5, molta sort!                                                                       |");
        System.out.println("|---------------------------------------------------------------------------------------------------------------------------|");
        mostrarBarraDeCarga(150);


        try {
            System.out.println("|    Quantitat de preguntes per la partida? ");
            int numPreguntes = Integer.parseInt(sc.nextLine());
            int contadorPreguntes = 0;

            // Obtener preguntas aleatorias
            List<Pregunta> preguntas = obtindrePreguntesAleatories(conn, numPreguntes);
            // Iniciar la partida
            int puntuacion = 0;
            int respuestasCorrectas = 0;
            int respuestasIncorrectas = 0;

            // Obtener un nuevo ID de partida
            int idPartida = obtenirNouIdPartida(conn);

            // Obtener la fecha y hora actual
            java.util.Date date = new java.util.Date();
            java.sql.Timestamp timestamp = new java.sql.Timestamp(date.getTime());

            // Insertar la nueva partida en la tabla partides
            String queryInsertPartida = "INSERT INTO partides (id_partida, id_usuari, mode_joc, data_hora_partida, num_preguntes, respostes_correctes, respostes_incorrectes) " +
                    "VALUES (?, (SELECT ID_usuari FROM usuaris WHERE nickname = ?), 'normal', ?, ?, 0, 0)";
            try (PreparedStatement pstmt = conn.prepareStatement(queryInsertPartida)) {
                pstmt.setInt(1, idPartida);
                pstmt.setString(2, nicknameSesion);
                pstmt.setTimestamp(3, timestamp);
                pstmt.setInt(4, numPreguntes);

                int filasAfectadas = pstmt.executeUpdate();
                if (filasAfectadas > 0) {
                } else {
                    System.out.println("|     Error en la creació de la partida. Torna a intentar-ho.");
                }
            }

            for (Pregunta pregunta : preguntas) {
                System.out.println(pregunta.getPregunta());
                List<Resposta> respuestas = obtindreRespostes(conn, pregunta.getIdPregunta());

                Collections.shuffle(respuestas);


                for (int i = 0; i < respuestas.size(); i++) {
                    System.out.println((i + 1) + ". " + respuestas.get(i).getResposta());
                }


                System.out.println("|     Selecciona la resposta correcta (1-" + respuestas.size() + "):");
                int respuestaUsuario = Integer.parseInt(sc.nextLine());


                if (respuestaUsuario >= 1 && respuestaUsuario <= respuestas.size()) {
                    if (respuestas.get(respuestaUsuario - 1).isCorrecta() == 1) {
                        System.out.println(GREEN_BRIGHT+"|     ¡Correcte!");
                        System.out.println(RESET);
                        puntuacion += pregunta.getPuntuacio();
                        respuestasCorrectas++;
                        contadorPreguntes++;
                        System.out.println();
                    } else {
                        System.out.println(RED_BRIGHT+"|     Incorrecte.");
                        System.out.println(RESET);
                        respuestasIncorrectas++;
                        contadorPreguntes++;
                        System.out.println();
                    }
                } else if(respuestaUsuario==5){
                    System.out.println("|     Sortint...");
                    llimpiarpantalla();
                    break;
                }
                else {
                    System.out.println("|     Opció no vàlida. Torna a seleccionar una resposta.");
                    System.out.println();
                }
            }

            int puntuacionTotal = puntuacion;


            String queryUpdatePuntuacionTotal = "UPDATE partides SET respostes_correctes = ?, respostes_incorrectes = ?, puntuacio = ? WHERE id_partida = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(queryUpdatePuntuacionTotal)) {
                pstmt.setInt(1, respuestasCorrectas);
                pstmt.setInt(2, respuestasIncorrectas);
                pstmt.setInt(3, puntuacionTotal);
                pstmt.setInt(4, idPartida);

                int filasAfectadas = pstmt.executeUpdate();
                if (filasAfectadas > 0) {
                } else {
                    System.out.println("|     Error en l'actualització de les estadístiques.");
                }
            }

            System.out.println("|     Partida finalitzada. Puntuació: " + puntuacion);
            llimpiarpantalla();
            if (contadorPreguntes==numPreguntes) {
                actualitzarEstadistiquesJugador(conn, nicknameSesion, respuestasCorrectas, idPartida, puntuacion, respuestasIncorrectas);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void actualitzarEstadistiquesJugador(Connection conn, String nicknameSesion, int respuestasCorrectas, int respuestasIncorrectas, int idPartida, int puntuacio) throws SQLException {
        int idJugador = obtindreIdJugador(conn, nicknameSesion);
        if(respuestasCorrectas >= respuestasIncorrectas){
            String queryUpdateVictoriesJugador = "UPDATE estadistiques " + "SET victories = victories + 1";
        }
        String queryUpdateEstadisticasJugador = "UPDATE estadistiques " +
                "SET partides_jugades = partides_jugades + 1, " +
                "preguntes_correctes = preguntes_correctes + ?, " +
                "punts_normals = punts_normals + ? " +
                "WHERE id_usuari = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(queryUpdateEstadisticasJugador)) {
            pstmt.setInt(1, respuestasCorrectas);
            pstmt.setInt(2, puntuacio);
            pstmt.setInt(3, idJugador);

            int filasAfectadas = pstmt.executeUpdate();
            if (filasAfectadas > 0) {
            } else {
                System.out.println("|     Error al actualitzar les estadístiques del jugador.");
                llimpiarpantalla();
            }
        }
    }
    public static int obtindreIdJugador(Connection conn, String nicknameSesion) throws SQLException {
        int idJugador = -1;
        String queryIdUsuario = "SELECT id_usuari FROM usuaris WHERE nickname = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(queryIdUsuario)) {
            pstmt.setString(1, nicknameSesion);
            try (ResultSet resultSet = pstmt.executeQuery()) {
                if (resultSet.next()) {
                    idJugador = resultSet.getInt("id_usuari");
                }
            }
        }

        return idJugador;
    }

    public static int obtenirNouIdPartida(Connection conn) throws SQLException {
        String queryMaxId = "SELECT MAX(id_partida) FROM partides";
        try (Statement stmt = conn.createStatement();
             ResultSet resultSet = stmt.executeQuery(queryMaxId)) {
            int nuevoId = 1;
            if (resultSet.next()) {
                nuevoId = resultSet.getInt(1) + 1;
            }
            return nuevoId;
        }
    }


    private static List<Pregunta> obtindrePreguntesAleatories(Connection conn, int numPreguntes) throws SQLException {
        List<Pregunta> preguntes = new ArrayList<>();
        String query = "SELECT * FROM preguntes ORDER BY RAND() LIMIT ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, numPreguntes);

            try (ResultSet resultSet = pstmt.executeQuery()) {
                while (resultSet.next()) {
                    int idPregunta = resultSet.getInt("id_pregunta");
                    String textPregunta = resultSet.getString("pregunta");
                    int idCategoria = resultSet.getInt("id_categoria");
                    int puntuacio = resultSet.getInt("puntuacio");
                    preguntes.add(new Pregunta(idPregunta, textPregunta, idCategoria, puntuacio));
                }
            }
        }
        return preguntes;
    }

    private static List<Resposta> obtindreRespostes(Connection conn, int idPregunta) throws SQLException {
        List<Resposta> respostes = new ArrayList<>();
        String query = "SELECT * FROM respostes WHERE id_pregunta = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, idPregunta);

            try (ResultSet resultSet = pstmt.executeQuery()) {
                while (resultSet.next()) {
                    int idResposta = resultSet.getInt("id_resposta");
                    String textResposta = resultSet.getString("resposta");
                    int correcta = resultSet.getInt("correcta");
                    respostes.add(new Resposta(idResposta, idPregunta, textResposta, correcta));
                }
            }
        }
        return respostes;
    }


    // Clase Pregunta
    static class Pregunta {
        private final int idPregunta;
        private final String pregunta;
        private final int idCategoria;
        private final int puntuacio;

        public Pregunta(int idPregunta, String pregunta, int idCategoria, int puntuacio) {
            this.idPregunta = idPregunta;
            this.pregunta = pregunta;
            this.idCategoria = idCategoria;
            this.puntuacio = puntuacio;
        }

        public int getIdPregunta() {
            return idPregunta;
        }

        public String getPregunta() {
            return pregunta;
        }

        public int getIdCategoria() {
            return idCategoria;
        }

        public int getPuntuacio() {
            return puntuacio;
        }
    }

    // Clase Resposta
    static class Resposta {
        private final int idResposta;
        private final int idPregunta;
        private final String resposta;
        private final int correcta;

        public Resposta(int idResposta, int idPregunta, String resposta, int correcta) {
            this.idResposta = idResposta;
            this.idPregunta = idPregunta;
            this.resposta = resposta;
            this.correcta = correcta;
        }

        public int getIdResposta() {
            return idResposta;
        }

        public int getIdPregunta() {
            return idPregunta;
        }

        public String getResposta() {
            return resposta;
        }

        public int isCorrecta() {
            return correcta;
        }
    }
    private static final int TEMPS_LIMIT_PREGUNTA = 10; // Tiempo límite en segundos por pregunta

    public static void iniciarPartidaCompetitiva(Connection conn, String nicknameSesion, Scanner sc) throws SQLException {
        System.out.println("|---------------------------------------------------------------------------------------------------------------------------|");
        System.out.println("|     Has escollit la modalitat de partida 'Competitiva'. Aquest mode consta de 30 preguntes i tens 30 segons per a         |");
        System.out.println("|     respondre cada una d'elles. Recorda que si falles et restara la puntuació correspondent de cada pregunta.             |");
        System.out.println("|     Obtindràs punt competitius que serviran per a classificar-te per a les finals del concursillo. Per a sortir           |");
        System.out.println("|     nomes has de fer click al 5, molta sort!                                                                              |");
        System.out.println("|---------------------------------------------------------------------------------------------------------------------------|");
        mostrarBarraDeCarga(150);

        try {

            int numPreguntes = 30;
            int contadorPreguntes = 0;

            // Obtener preguntas aleatorias
            List<Pregunta> preguntes = obtindrePreguntesAleatories(conn, numPreguntes);
            // Iniciar la partida
            int puntuacio = 0;
            int respostesCorrectes = 0;
            final int[] respostesIncorrectes = {0};

            // Obtener un nuevo ID de partida
            int idPartida = obtenirNouIdPartida(conn);

            // Obtener la fecha y hora actual
            java.util.Date date = new java.util.Date();
            java.sql.Timestamp timestamp = new java.sql.Timestamp(date.getTime());

            // Insertar la nueva partida en la tabla partides
            String queryInsertPartida = "INSERT INTO partides (id_partida, id_usuari, mode_joc, data_hora_partida, num_preguntes, respostes_correctes, respostes_incorrectes) " +
                    "VALUES (?, (SELECT ID_usuari FROM usuaris WHERE nickname = ?), 'competitivo', ?, ?, 0, 0)";
            try (PreparedStatement pstmt = conn.prepareStatement(queryInsertPartida)) {
                pstmt.setInt(1, idPartida);
                pstmt.setString(2, nicknameSesion);
                pstmt.setTimestamp(3, timestamp);
                pstmt.setInt(4, numPreguntes);

                int filasAfectadas = pstmt.executeUpdate();
                if (filasAfectadas > 0) {

                } else {
                    System.out.println("|     Error en la creació de la partida. Inténta-ho de nou.");
                }
            }


            for (Pregunta pregunta : preguntes) {
                System.out.println(pregunta.getPregunta());
                List<Resposta> respostes = obtindreRespostes(conn, pregunta.getIdPregunta());


                Collections.shuffle(respostes);


                for (int i = 0; i < respostes.size(); i++) {
                    System.out.println((i + 1) + ". " + respostes.get(i).getResposta());
                }


                final boolean[] respos = {false};


                boolean[] tempsEsgotat = {false};


                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        if (!respos[0]) {
                            System.out.println("|     ¡Temps esgotat!");
                            System.out.println("|     Prem enter per a la següent pregunta.");
                            System.out.println();
                            tempsEsgotat[0] = true;
                            timer.cancel();
                            respostesIncorrectes[0]++;
                        }
                    }
                }, TEMPS_LIMIT_PREGUNTA * 1000);


                System.out.println("|     Selecciona la resposta correcta (1-" + respostes.size() + "):");
                String respostesStr = sc.nextLine();
                if (respostesStr.isEmpty()) {

                    continue;
                }

                int respostaUsuari = Integer.parseInt(respostesStr);
                respos[0] = true;


                timer.cancel();


                if (tempsEsgotat[0]) {
                    continue;
                }


                if (respostaUsuari >= 1 && respostaUsuari <= respostes.size()) {
                    if (respostes.get(respostaUsuari - 1).isCorrecta() == 1) {
                        System.out.println(GREEN_BRIGHT+"|     ¡Correcte!");
                        System.out.println(RESET);
                        puntuacio += pregunta.getPuntuacio();
                        respostesCorrectes++;
                        System.out.println();
                    } else {
                        System.out.println(RED_BRIGHT+"|     ¡Incorrecte!");
                        System.out.println(RESET);
                        respostesIncorrectes[0]++;
                        puntuacio -= pregunta.getPuntuacio();
                        System.out.println();
                    }
                } else if(respostaUsuari==5){
                    System.out.println("|     Sortint...");
                    llimpiarpantalla();
                    break;
                }
                else {
                    System.out.println("|     Opció no válida. Torna a seleccionar una respuesta.");
                }
            }

            int puntuacioTotal = puntuacio;

            String queryUpdatePuntuacionTotal = "UPDATE partides SET respostes_correctes = ?, respostes_incorrectes = ?, puntuacio = ? WHERE id_partida = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(queryUpdatePuntuacionTotal)) {
                pstmt.setInt(1, respostesCorrectes);
                pstmt.setInt(2, respostesIncorrectes[0]);
                pstmt.setInt(3, puntuacioTotal);
                pstmt.setInt(4, idPartida);

                int filasAfectadas = pstmt.executeUpdate();
                if (filasAfectadas > 0) {
                } else {
                    System.out.println("|     Error en la actualizació de les estadístiques.");
                }
            }

            System.out.println("|     Partida finalitzada. Puntuació: " + puntuacio);

            if(contadorPreguntes==numPreguntes) {
                actualitzarEstadistiquesJugador(conn, nicknameSesion, respostesCorrectes, idPartida, puntuacio, respostesIncorrectes[0]);
            } else{
                System.out.println("|     La puntuació no s'actualitzara ja que has sortit de la partida.");
                System.out.println();
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void editarPermisos(Connection conn, Statement stat, Scanner sc) throws SQLException {
        veureUsuarisSimple(conn, stat);
        System.out.println("|     Introdueix el nom del usuari al que vols cambiar els permisos: ");
        String nickname = sc.nextLine();
        boolean esAdministrador = obtenirEstatAdministrador(conn, stat, sc, nickname);
        String sn = " ";
        if (!esAdministrador) {
            System.out.println("|     Vols donar els permisos d'administrador? s/n");
            sn = sc.nextLine();
            if (sn.equals("s")) {
                donarPermisosAdmin(conn, stat, sc, nickname);
            } else if (sn.equals("n")) {
                System.out.println("|     D'acord");
            } else {
                System.out.println("|     lletra no vàlida, torna a introduirla");
            }
        } else {
            System.out.println("|     Vols treure els permisos d'administrador? s/n");
            sn = sc.nextLine();
            if (sn.equals("s")) {
                treurePermisosAdmin(conn, stat, sc, nickname);
            } else if (sn.equals("n")) {
                System.out.println("|     D'acord");
            } else {
                System.out.println("|     lletra no vàlida");
            }
        }
    }

    public static void donarPermisosAdmin(Connection conn, Statement stat, Scanner sc, String username) throws SQLException {
        String query = "UPDATE usuaris SET admin = 1 WHERE nickname = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, username);
            int filasActualizadas = pstmt.executeUpdate();
            if (filasActualizadas > 0) {
                System.out.println("|     S'han otorgat els permisos d'administrador a \" + username + \".");
            } else {
                System.out.println("|     No s'han pogut otorgar els permisos d'administrador a " + username + ".");
            }
        }
    }

    public static void treurePermisosAdmin(Connection conn, Statement stat, Scanner sc, String username) throws SQLException {
        String query = "UPDATE usuaris SET admin = 0 WHERE nickname = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, username);
            int filasActualizadas = pstmt.executeUpdate();
            if (filasActualizadas > 0) {
                System.out.println("|     S'han tret els permisos d'administrador a " + username + ".");
            } else {
                System.out.println("|     No s'han pogut treure els permisos d'administrador a " + username + ".");
            }
        }
    }
    public static void esborrarUsuari(Connection conn, Statement stat, Scanner sc) throws SQLException {
        veureUsuarisSimple(conn, stat);
        System.out.println("|     Introdueix el nickname de l'usuari que vols esborrar:");
        String nickname = sc.nextLine();
        int idJugador = obtindreIdJugador(conn, nickname);

        // Eliminar las partidas del usuario
        String deletePartidesQuery = "DELETE FROM partides WHERE id_usuari = (SELECT ID_usuari FROM usuaris WHERE nickname = ?)";
        try (PreparedStatement deletePartidesStmt = conn.prepareStatement(deletePartidesQuery)) {
            deletePartidesStmt.setString(1, nickname);
            int partidesEliminades = deletePartidesStmt.executeUpdate();
            System.out.println("|     "+partidesEliminades + " partides eliminades per l'usuari " + nickname);
        }

        // Eliminar las estadísticas del usuario
        String deleteEstadistiquesQuery = "DELETE FROM estadistiques WHERE id_usuari = (SELECT ID_usuari FROM usuaris WHERE nickname = ?)";
        try (PreparedStatement deleteEstadistiquesStmt = conn.prepareStatement(deleteEstadistiquesQuery)) {
            deleteEstadistiquesStmt.setString(1, nickname);
            int estadistiquesEliminades = deleteEstadistiquesStmt.executeUpdate();
            System.out.println("|     "+estadistiquesEliminades + " estadístiques eliminades per l'usuari " + nickname);
        }

        // Eliminar al usuario
        String deleteUsuariQuery = "DELETE FROM usuaris WHERE nickname = ?";
        try (PreparedStatement deleteUsuariStmt = conn.prepareStatement(deleteUsuariQuery)) {
            deleteUsuariStmt.setString(1, nickname);
            int usuariEliminat = deleteUsuariStmt.executeUpdate();
            if (usuariEliminat > 0) {
                System.out.println("|     Usuari " + nickname + " esborrat amb èxit.");
            } else {
                System.out.println("|     No s'ha pogut esborrar l'usuari " + nickname + ".");
            }
        }
    }

    public static void veureUsuaris(Connection conn, Statement stat) throws SQLException {
        String sql = "SELECT * FROM `usuaris`";
        ResultSet rs = stat.executeQuery(sql);
        while (rs.next()) {
            int id_usuari = rs.getInt("id_usuari");
            String nickname = rs.getString("nickname");
            String nom = rs.getString("nom");
            String cognom = rs.getString("cognom");
            String contrasenya = rs.getString("contrasenya");
            String pais = rs.getString("pais");
            int edat = rs.getInt("edat");
            String data_registre = rs.getString("data_registre");
            String correu_electronic = rs.getString("correu_electronic");
            int status = rs.getInt("status");
            int admin = rs.getInt("admin");
            System.out.println("|     ID: " + id_usuari);
            System.out.println("|     nickname: " + nickname);
            System.out.println("|     nom: " + nom);
            System.out.println("|     cognom: " + cognom);
            System.out.println("|     contrasenya: " + contrasenya);
            System.out.println("|     pais: " + pais);
            System.out.println("|     edat: " + edat);
            System.out.println("|     data_registre: " + data_registre);
            System.out.println("|     correu_electronic: " + correu_electronic);
            if (status == 1) {
                System.out.println("|     L'usuari esta conectat");
            } else {
                System.out.println("|     L'usuari no esta conectat");
            }
            if (admin == 1) {
                System.out.println("|     L'usuari es administrador");
            } else {
                System.out.println("|     L'usuari no es administrador");
            }

            System.out.println("|---------------------------------------------------------------------------------------------------------------------------|");
        }
    }
    public static void veureUsuarisSimple(Connection conn, Statement stat) throws SQLException {
        String sql = "SELECT id_usuari,nickname FROM `usuaris`";
        ResultSet rs = stat.executeQuery(sql);
        while (rs.next()) {
            int id_usuari = rs.getInt("id_usuari");
            String nickname = rs.getString("nickname");
            System.out.println("|     ID: " + id_usuari);
            System.out.println("|     nickname: " + nickname);
            System.out.println("|---------------------------------------------------------------------------------------------------------------------------|");
        }
    }
    private static Pregunta obtindrePreguntaAleatoria(Connection conn) throws SQLException {
        Pregunta pregunta = null;
        String query = "SELECT * FROM preguntes ORDER BY RAND() LIMIT 1";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            try (ResultSet resultSet = pstmt.executeQuery()) {
                if (resultSet.next()) {
                    int idPregunta = resultSet.getInt("id_pregunta");
                    String textPregunta = resultSet.getString("pregunta");
                    int idCategoria = resultSet.getInt("id_categoria");
                    int puntuacio = resultSet.getInt("puntuacio");
                    pregunta = new Pregunta(idPregunta, textPregunta, idCategoria, puntuacio);
                }
            }
        }
        return pregunta;
    }

    public static void iniciarPartidaSurvival(Connection conn, String nicknameSesion, Scanner sc) throws SQLException {
        System.out.println("|---------------------------------------------------------------------------------------------------------------------------|");
        System.out.println("|     Has escollit la modalitat de partida 'Survival'. Aquest mode nomes acaba si falles alguna pregunta o si prems         |");
        System.out.println("|     la tecla '5'. La puntuació que conseguiras es normal. Molta sort!                                                     |");
        System.out.println("|---------------------------------------------------------------------------------------------------------------------------|");
        mostrarBarraDeCarga(150);
        try {
            // Obtener un nuevo ID de partida
            int idPartida = obtenirNouIdPartida(conn);

            // Obtener la fecha y hora actual
            java.util.Date date = new java.util.Date();
            java.sql.Timestamp timestamp = new java.sql.Timestamp(date.getTime());

            // Insertar la nueva partida en la tabla partides
            String queryInsertPartida = "INSERT INTO partides (id_partida, id_usuari, mode_joc, data_hora_partida, num_preguntes, respostes_correctes, respostes_incorrectes) " +
                    "VALUES (?, (SELECT ID_usuari FROM usuaris WHERE nickname = ?), 'normal', ?, ?, 0, 0)";
            try (PreparedStatement pstmt = conn.prepareStatement(queryInsertPartida)) {
                pstmt.setInt(1, idPartida);
                pstmt.setString(2, nicknameSesion);
                pstmt.setTimestamp(3, timestamp);
                pstmt.setInt(4, 0); // Establecer el número de preguntas como 0 inicialmente

                int filasAfectadas = pstmt.executeUpdate();
                if (filasAfectadas > 0) {

                } else {
                    System.out.println("|     Error en la creació de la partida. Torna a intentar-ho.");
                }
            }

            int respuestasCorrectas = 0;
            int respuestasIncorrectas = 0;
            int puntuacionTotal = 0;


            while (true) {

                Pregunta pregunta = obtindrePreguntaAleatoria(conn);

                System.out.println(pregunta.getPregunta());
                List<Resposta> respostes = obtindreRespostes(conn, pregunta.getIdPregunta());


                Collections.shuffle(respostes);


                for (int i = 0; i < respostes.size(); i++) {
                    System.out.println((i + 1) + ". " + respostes.get(i).getResposta());
                }


                System.out.println("|     Selecciona la resposta correcta (1-" + respostes.size() + "):");
                int respostaUsuari = Integer.parseInt(sc.nextLine());

                // Verificar si la respuesta es válida antes de acceder al ArrayList
                if (respostaUsuari >= 1 && respostaUsuari <= respostes.size()) {
                    if (respostes.get(respostaUsuari - 1).isCorrecta() == 1) {
                        System.out.println(GREEN_BRIGHT+"|     ¡Correcte!");
                        System.out.println(RESET);
                        respuestasCorrectas++;
                        puntuacionTotal += pregunta.getPuntuacio();
                    } else {
                        System.out.println(RED_BRIGHT+"|     Incorrecte.");
                        System.out.println(RESET);
                        respuestasIncorrectas++;
                        break;
                    }
                } else if(respostaUsuari==5){
                    System.out.println("|     Sortint...");
                    break;
                }
                else {
                    System.out.println("|     Opció no vàlida. Torna a seleccionar una resposta.");
                }
            }

            // Actualizar la tabla partides con las estadísticas finales
            String queryUpdatePuntuacionTotal = "UPDATE partides SET respostes_correctes = ?, respostes_incorrectes = ?, puntuacio = ? WHERE id_partida = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(queryUpdatePuntuacionTotal)) {
                pstmt.setInt(1, respuestasCorrectas);
                pstmt.setInt(2, respuestasIncorrectas);
                pstmt.setInt(3, puntuacionTotal);
                pstmt.setInt(4, idPartida);

                int filasAfectadas = pstmt.executeUpdate();
                if (filasAfectadas > 0) {
                    System.out.println("|     Estadísticas actualizadas amb èxit.");
                } else {
                    System.out.println("|     Error en l'actualització de les estadístiques.");
                }
            }

            System.out.println("|     Partida finalitzada. Puntuació: " + puntuacionTotal);

            actualitzarEstadistiquesJugador(conn, nicknameSesion, respuestasCorrectas, idPartida, puntuacionTotal, respuestasIncorrectas);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void iniciarPartidaCategoria(Connection conn, String nicknameSesion, Scanner sc)throws SQLException{
        System.out.println("|---------------------------------------------------------------------------------------------------------------------------|");
        System.out.println("|     Has escollit la modalitat de partida 'Per Categoria'. Aquest mode mostrara preguntes de la categoria que              |");
        System.out.println("|     escolliu. Per a sortir de la partida prem la tecla '5'.                                                               |");
        System.out.println("|---------------------------------------------------------------------------------------------------------------------------|");
        mostrarBarraDeCarga(150);
        mostrarTotesCategories(conn);
        System.out.println("|     Elegeix l'ID de la categoria que vols jugar: ");
        int categoria = sc.nextInt();
        sc.nextLine();
        try {
            System.out.println("|     Quantitat de preguntes per la partida?");
            int numPreguntes = Integer.parseInt(sc.nextLine());

            // Obté preguntes aleatòries de la categoria amb ID 1
            List<Pregunta> preguntas = obtindrePreguntesCategoria(conn, numPreguntes, categoria);
            // Iniciar la partida
            int puntuacio = 0;
            int respostesCorrectes = 0;
            int respostesIncorrectes = 0;

            // Obtener un nuevo ID de partida
            int idPartida = obtenirNouIdPartida(conn);

            // Obtener la fecha y hora actual
            java.util.Date date = new java.util.Date();
            java.sql.Timestamp timestamp = new java.sql.Timestamp(date.getTime());

            // Insertar la nueva partida en la tabla partides
            String queryInsertPartida = "INSERT INTO partides (id_partida, id_usuari, mode_joc, data_hora_partida, num_preguntes, respostes_correctes, respostes_incorrectes) " +
                    "VALUES (?, (SELECT ID_usuari FROM usuaris WHERE nickname = ?), 'normal', ?, ?, 0, 0)";
            try (PreparedStatement pstmt = conn.prepareStatement(queryInsertPartida)) {
                pstmt.setInt(1, idPartida);
                pstmt.setString(2, nicknameSesion);
                pstmt.setTimestamp(3, timestamp);
                pstmt.setInt(4, numPreguntes);

                int filasAfectadas = pstmt.executeUpdate();
                if (filasAfectadas > 0) {

                }
                else {
                    System.out.println("|     Error en la creació de la partida. Torna a intentar-ho.");
                }
            }

            for (Pregunta pregunta : preguntas) {
                System.out.println(pregunta.getPregunta());
                List<Resposta> respuestas = obtindreRespostes(conn, pregunta.getIdPregunta());

                Collections.shuffle(respuestas);


                for (int i = 0; i < respuestas.size(); i++) {
                    System.out.println((i + 1) + ". " + respuestas.get(i).getResposta());
                }


                System.out.println("|     Selecciona la resposta correcta (1-" + respuestas.size() + "):");
                int respostaUsuari = Integer.parseInt(sc.nextLine());


                if (respostaUsuari >= 1 && respostaUsuari <= respuestas.size()) {
                    if (respuestas.get(respostaUsuari - 1).isCorrecta() == 1) {
                        System.out.println(GREEN_BRIGHT+"|     ¡Correcte!");
                        System.out.println(RESET);
                        puntuacio += pregunta.getPuntuacio();
                        respostesCorrectes++;
                        System.out.println();
                    } else {
                        System.out.println(RED_BRIGHT+"|     Incorrecte.");
                        System.out.println(RESET);
                        System.out.println();
                        respostesIncorrectes++;
                    }
                } else if(respostaUsuari==5){
                    System.out.println("|     Sortint...");
                    llimpiarpantalla();
                    break;
                }
                else {
                    System.out.println("|     Opció no vàlida. Torna a seleccionar una resposta.");
                }
            }

            int puntuacioTotal = puntuacio;


            String queryUpdatePuntuacionTotal = "UPDATE partides SET respostes_correctes = ?, respostes_incorrectes = ?, puntuacio = ? WHERE id_partida = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(queryUpdatePuntuacionTotal)) {
                pstmt.setInt(1, respostesCorrectes);
                pstmt.setInt(2, respostesIncorrectes);
                pstmt.setInt(3, puntuacioTotal);
                pstmt.setInt(4, idPartida);

                int filasAfectadas = pstmt.executeUpdate();
                if (filasAfectadas > 0) {
                    System.out.println("|     Estadísticas actualizadas amb èxit.");
                } else {
                    System.out.println("|     Error en l'actualització de les estadístiques.");
                }
            }

            System.out.println("|     Partida finalitzada. Puntuació: " + puntuacio);

            actualitzarEstadistiquesJugador(conn, nicknameSesion, respostesCorrectes, idPartida, puntuacio, respostesIncorrectes);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Pregunta> obtindrePreguntesCategoria(Connection conn, int numPreguntes, int idCategoria) throws SQLException {
        List<Pregunta> preguntas = new ArrayList<>();
        String query = "SELECT * FROM preguntes WHERE id_categoria = ? ORDER BY RAND() LIMIT ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, idCategoria);
            pstmt.setInt(2, numPreguntes);
            try (ResultSet resultSet = pstmt.executeQuery()) {
                while (resultSet.next()) {
                    int idPregunta = resultSet.getInt("id_pregunta");
                    String textoPregunta = resultSet.getString("pregunta");
                    int idCategoriaPregunta = resultSet.getInt("id_categoria");
                    int puntuacio = resultSet.getInt("puntuacio");
                    Pregunta pregunta = new Pregunta(idPregunta, textoPregunta, idCategoriaPregunta, puntuacio);
                    preguntas.add(pregunta);
                }
            }
        }
        return preguntas;
    }
    public static void mostrarTotesCategories(Connection conn) throws SQLException {
        // Declarar l'enunciat i el conjunt de resultats
        try (Statement stmt = conn.createStatement()) {
            // Executar la consulta SQL
            String sql = "SELECT ID_categoria,categoria FROM categories";
            ResultSet rs = stmt.executeQuery(sql);

            // Imprimir les categories
            System.out.println("Categories:");
            while (rs.next()) {
                int ID_categoria = rs.getInt("ID_categoria");
                String categoria = rs.getString("categoria");
                System.out.println("|     ID: "+ ID_categoria + "| "+categoria);
            }
        }
    }
    public static final String CYAN_BOLD_BRIGHT = "\033[1;96m"; // CYAN
    public static final String GREEN_BRIGHT = "\033[0;92m"; // GREEN

    public static final String RED_BRIGHT = "\033[0;91m"; // RED
    public static final String RESET = "\u001B[0m";
    public static final String YELLOW_BRIGHT = "\033[0;93m"; // YELLOW
    public static void mostrarBarraDeCarga(int total) {
        for (int i = 0; i < total; i++) {
            System.out.print(CYAN_BOLD_BRIGHT+"-");

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(RESET);

        llimpiarpantalla();
    }

    public static void llimpiarpantalla() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }
}