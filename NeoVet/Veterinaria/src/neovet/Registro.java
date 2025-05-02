package neovet;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Registro extends Application {
    // Definir el color azul específico de NEOVET
    private final Color NEOVET_BLUE = Color.rgb(31, 93, 167);
    
    // Convertir el color a cadena hexadecimal para CSS
    private final String NEOVET_BLUE_HEX = String.format("#%02X%02X%02X",
        (int)(NEOVET_BLUE.getRed() * 255),
        (int)(NEOVET_BLUE.getGreen() * 255),
        (int)(NEOVET_BLUE.getBlue() * 255));
    
    @Override
    public void start(Stage primaryStage) {
        // Configuración básica de la ventana
        primaryStage.setTitle("NEOVET - Registro de Mascotas");
        
        // Crear un TabPane para organizar los datos en pestañas
        TabPane tabPane = new TabPane();
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        
        // Pestaña: Datos de la Mascota
        Tab tabMascota = new Tab("Datos de la Mascota");
        tabMascota.setContent(crearFormularioDatosMascota());
        
        // Pestaña: Datos Médicos
        Tab tabMedicos = new Tab("Datos Médicos");
        tabMedicos.setContent(crearFormularioDatosMedicos());
        
        // Pestaña: Datos del Dueño
        Tab tabDueno = new Tab("Datos del Dueño");
        tabDueno.setContent(crearFormularioDatosDueno());
        
        // Pestaña: Datos de la Cita
        Tab tabCita = new Tab("Datos de la Cita");
        tabCita.setContent(crearFormularioDatosCita());
        
        // Agregar las pestañas al TabPane
        tabPane.getTabs().addAll(tabMascota, tabMedicos, tabDueno, tabCita);
        
        // Panel principal que contiene el logo y el TabPane
        VBox mainContainer = new VBox(20);
        mainContainer.setAlignment(Pos.TOP_CENTER);
        mainContainer.setPadding(new Insets(20));
        mainContainer.setStyle("-fx-background-color: white;");
        
        // Cargar imagen del logo
        ImageView logoImageView = loadLogoImage();
        
        if (logoImageView != null) {
            // Crear un contenedor para centrar el logo
            HBox logoContainer = new HBox();
            logoContainer.setAlignment(Pos.CENTER);
            logoContainer.getChildren().add(logoImageView);
            mainContainer.getChildren().add(logoContainer);
        }
        
        // Agregar el TabPane al contenedor principal
        mainContainer.getChildren().add(tabPane);
        
        // Crear los botones de acción
        Button btnGuardar = new Button("Guardar");
        btnGuardar.setStyle(
            "-fx-background-color: " + NEOVET_BLUE_HEX + ";" +
            "-fx-text-fill: white;" +
            "-fx-background-radius: 25px;" +
            "-fx-font-family: 'Arial';" +
            "-fx-font-weight: bold;" +
            "-fx-font-size: 14px;"
        );
        btnGuardar.setPrefSize(150, 40);
        
        Button btnCancelar = new Button("Salir");
        btnCancelar.setStyle(
            "-fx-background-color: white;" +
            "-fx-text-fill: " + NEOVET_BLUE_HEX + ";" +
            "-fx-border-color: " + NEOVET_BLUE_HEX + ";" +
            "-fx-border-width: 2px;" +
            "-fx-border-radius: 25px;" +
            "-fx-background-radius: 25px;" +
            "-fx-font-family: 'Arial';" +
            "-fx-font-weight: bold;" +
            "-fx-font-size: 14px;"
        );
        btnCancelar.setPrefSize(150, 40);
        btnCancelar.setOnAction(e -> System.exit(0));
        
        // Crear un contenedor para los botones
        HBox botonesContainer = new HBox(20, btnGuardar, btnCancelar);
        botonesContainer.setAlignment(Pos.CENTER);
        botonesContainer.setPadding(new Insets(20, 0, 20, 0));
        
        // Agregar el contenedor de botones al contenedor principal
        mainContainer.getChildren().add(botonesContainer);
        
        // Configurar la escena
        Scene scene = new Scene(mainContainer, 800, 650);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    
    private ImageView loadLogoImage() {
        try {
            // Intenta cargar la imagen desde el mismo paquete
            Image logo = new Image(getClass().getResourceAsStream("logo.png"));
            ImageView imageView = new ImageView(logo);
            // Redimensionar manteniendo proporciones
            imageView.setFitWidth(300);
            imageView.setFitHeight(100);
            imageView.setPreserveRatio(true);
            return imageView;
        } catch (Exception e) {
            System.err.println("Error al cargar la imagen: " + e.getMessage());
            return null;
        }
    }
    
    private GridPane crearFormularioDatosMascota() {
        GridPane grid = new GridPane();
        grid.setHgap(20);
        grid.setVgap(15);
        grid.setPadding(new Insets(20));
        
        // Etiquetas y campos para Datos de la Mascota
        Label lblNombre = new Label("Nombre de la mascota:");
        TextField txtNombre = new TextField();
        txtNombre.setPromptText("Ej: Max");
        
        // Campo para especificar otra especie
        TextField txtOtraEspecie = new TextField();
        txtOtraEspecie.setPromptText("Especifique la especie");
        txtOtraEspecie.setVisible(false); // Inicialmente oculto
        
        Label lblEspecie = new Label("Especie:");
        ComboBox<String> cmbEspecie = new ComboBox<>();
        cmbEspecie.getItems().addAll("Perro", "Gato", "Ave", "Conejo", "Otro");
        cmbEspecie.setPromptText("Seleccione especie");
        
        // Mostrar u ocultar el campo de texto para otra especie según la selección
        cmbEspecie.setOnAction(e -> {
            if (cmbEspecie.getValue() != null && cmbEspecie.getValue().equals("Otro")) {
                txtOtraEspecie.setVisible(true);
            } else {
                txtOtraEspecie.setVisible(false);
                txtOtraEspecie.clear();
            }
        });
        
        Label lblRaza = new Label("Raza:");
        TextField txtRaza = new TextField();
        txtRaza.setPromptText("Ej: Labrador, Siamés");
        
        Label lblFechaNacimiento = new Label("Fecha de nacimiento:");
        DatePicker dateFechaNacimiento = new DatePicker();
        
        Label lblEdad = new Label("Edad:");
        TextField txtEdad = new TextField();
        txtEdad.setPromptText("Ej: 3 años");
        // Ahora el campo es editable
        
        
        Label lblSexo = new Label("Sexo:");
        HBox sexoContainer = new HBox(15);
        ToggleGroup sexoGroup = new ToggleGroup();
        RadioButton rbMacho = new RadioButton("Macho");
        RadioButton rbHembra = new RadioButton("Hembra");
        rbMacho.setToggleGroup(sexoGroup);
        rbHembra.setToggleGroup(sexoGroup);
        sexoContainer.getChildren().addAll(rbMacho, rbHembra);
        
        Label lblColor = new Label("Color:");
        TextField txtColor = new TextField();
        txtColor.setPromptText("Ej: Negro y café");
        
        Label lblPeso = new Label("Peso (kg):");
        TextField txtPeso = new TextField();
        txtPeso.setPromptText("Ej: 12.5");
        
        Label lblTamano = new Label("Tamaño:");
        ComboBox<String> cmbTamano = new ComboBox<>();
        cmbTamano.getItems().addAll("Pequeño", "Mediano", "Grande");
        cmbTamano.setPromptText("Seleccione tamaño");
        
        // Añadir todos los campos al grid
        int row = 0;
        grid.add(lblNombre, 0, row);
        grid.add(txtNombre, 1, row++);
        
        grid.add(lblEspecie, 0, row);
        
        // Contenedor para el combobox y el campo de texto adicional
        VBox especieContainer = new VBox(5);
        especieContainer.getChildren().addAll(cmbEspecie, txtOtraEspecie);
        grid.add(especieContainer, 1, row++);
        
        grid.add(lblRaza, 0, row);
        grid.add(txtRaza, 1, row++);
        
        grid.add(lblFechaNacimiento, 0, row);
        grid.add(dateFechaNacimiento, 1, row++);
        
        grid.add(lblEdad, 0, row);
        grid.add(txtEdad, 1, row++);
        
        grid.add(lblSexo, 0, row);
        grid.add(sexoContainer, 1, row++);
        
        grid.add(lblColor, 0, row);
        grid.add(txtColor, 1, row++);
        
        grid.add(lblPeso, 0, row);
        grid.add(txtPeso, 1, row++);
        
        grid.add(lblTamano, 0, row);
        grid.add(cmbTamano, 1, row++);
        
        // Estilos para las etiquetas
        grid.getChildren().filtered(node -> node instanceof Label).forEach(node -> 
            ((Label)node).setFont(Font.font("Arial", FontWeight.BOLD, 14))
        );
        
        return grid;
    }
    
    private GridPane crearFormularioDatosMedicos() {
        GridPane grid = new GridPane();
        grid.setHgap(20);
        grid.setVgap(15);
        grid.setPadding(new Insets(20));
        
        // Etiquetas y campos para Datos Médicos
        Label lblTipoSangre = new Label("Tipo de sangre:");
        ComboBox<String> cmbTipoSangre = new ComboBox<>();
        cmbTipoSangre.getItems().addAll("DEA 1.1+", "DEA 1.1-", "DEA 1.2+", "DEA 1.2-", "Tipo A", "Tipo B", "Tipo AB");
        cmbTipoSangre.setPromptText("Seleccione tipo de sangre");
        
        Label lblAlergias = new Label("Alergias:");
        TextArea txtAlergias = new TextArea();
        txtAlergias.setPromptText("Ej: Penicilina");
        txtAlergias.setPrefRowCount(3);
        
        Label lblEnfermedades = new Label("Enfermedades crónicas:");
        TextField txtEnfermedades = new TextField();
        txtEnfermedades.setPromptText("Ej: Diabetes");
        
        Label lblVacunas = new Label("Vacunas al día:");
        CheckBox chkVacunas = new CheckBox("Sí");
        
        Label lblUltimaVisita = new Label("Última visita:");
        DatePicker dateUltimaVisita = new DatePicker();
        
        Label lblMicrochip = new Label("Microchip:");
        TextField txtMicrochip = new TextField();
        txtMicrochip.setPromptText("Ej: 123456789");
        
        // Añadir todos los campos al grid
        int row = 0;
        grid.add(lblTipoSangre, 0, row);
        grid.add(cmbTipoSangre, 1, row++);
        
        grid.add(lblAlergias, 0, row);
        grid.add(txtAlergias, 1, row++);
        
        grid.add(lblEnfermedades, 0, row);
        grid.add(txtEnfermedades, 1, row++);
        
        grid.add(lblVacunas, 0, row);
        grid.add(chkVacunas, 1, row++);
        
        grid.add(lblUltimaVisita, 0, row);
        grid.add(dateUltimaVisita, 1, row++);
        
        grid.add(lblMicrochip, 0, row);
        grid.add(txtMicrochip, 1, row++);
        
        // Estilos para las etiquetas
        grid.getChildren().filtered(node -> node instanceof Label).forEach(node -> 
            ((Label)node).setFont(Font.font("Arial", FontWeight.BOLD, 14))
        );
        
        return grid;
    }
    
    private GridPane crearFormularioDatosDueno() {
        GridPane grid = new GridPane();
        grid.setHgap(20);
        grid.setVgap(15);
        grid.setPadding(new Insets(20));
        
        // Etiquetas y campos para Datos del Dueño
        Label lblNombreDueno = new Label("Nombre del dueño:");
        TextField txtNombreDueno = new TextField();
        txtNombreDueno.setPromptText("Ej: Maria Gonzalez");
        
        Label lblTelefono = new Label("Teléfono:");
        TextField txtTelefono = new TextField();
        txtTelefono.setPromptText("Ej: 312 2615381");
        
        Label lblEmail = new Label("Correo electrónico:");
        TextField txtEmail = new TextField();
        txtEmail.setPromptText("Ej: ejemplo@example.com");
        
        Label lblDireccion = new Label("Dirección:");
        TextArea txtDireccion = new TextArea();
        txtDireccion.setPromptText("Ej: Calle Falsa 123");
        txtDireccion.setPrefRowCount(3);
        
        Label lblRFC = new Label("RFC (para facturación):");
        TextField txtRFC = new TextField();
        txtRFC.setPromptText("Ej: GOMJ800101");
        
        // Añadir todos los campos al grid
        int row = 0;
        grid.add(lblNombreDueno, 0, row);
        grid.add(txtNombreDueno, 1, row++);
        
        grid.add(lblTelefono, 0, row);
        grid.add(txtTelefono, 1, row++);
        
        grid.add(lblEmail, 0, row);
        grid.add(txtEmail, 1, row++);
        
        grid.add(lblDireccion, 0, row);
        grid.add(txtDireccion, 1, row++);
        
        grid.add(lblRFC, 0, row);
        grid.add(txtRFC, 1, row++);
        
        // Estilos para las etiquetas
        grid.getChildren().filtered(node -> node instanceof Label).forEach(node -> 
            ((Label)node).setFont(Font.font("Arial", FontWeight.BOLD, 14))
        );
        
        return grid;
    }
    
    private GridPane crearFormularioDatosCita() {
        GridPane grid = new GridPane();
        grid.setHgap(20);
        grid.setVgap(15);
        grid.setPadding(new Insets(20));
        
        // Etiquetas y campos para Datos de la Cita
        Label lblMotivo = new Label("Motivo de la consulta:");
        ComboBox<String> cmbMotivo = new ComboBox<>();
        cmbMotivo.getItems().addAll("Urgencia", "Control", "Cirugía", "Vacunación", "Desparasitación");
        cmbMotivo.setPromptText("Seleccione motivo");
        
        // NUEVO: Estado de la cita
        Label lblEstadoCita = new Label("Estado de la cita:");
        ComboBox<String> cmbEstadoCita = new ComboBox<>();
        cmbEstadoCita.getItems().addAll("Por tener", "En proceso", "Terminada", "Cancelada");
        cmbEstadoCita.setPromptText("Seleccione estado");
        cmbEstadoCita.setValue("Por tener"); // Valor por defecto
        
        Label lblSintomas = new Label("Síntomas:");
        TextArea txtSintomas = new TextArea();
        txtSintomas.setPromptText("Ej: Vómito, diarrea");
        txtSintomas.setPrefRowCount(3);
        
        Label lblMedicamentos = new Label("Medicamentos actuales:");
        TextField txtMedicamentos = new TextField();
        txtMedicamentos.setPromptText("Ej: Omeprazol");
        
        Label lblVeterinario = new Label("Veterinario asignado:");
        ComboBox<String> cmbVeterinario = new ComboBox<>();
        cmbVeterinario.getItems().addAll("Dr. Pérez", "Dra. González", "Dr. Rodríguez");
        cmbVeterinario.setPromptText("Seleccione veterinario");
        
        Label lblFechaHora = new Label("Fecha y hora de la cita:");
        HBox fechaHoraContainer = new HBox(10);
        DatePicker dateFechaCita = new DatePicker();
        ComboBox<String> cmbHoraCita = new ComboBox<>();
        cmbHoraCita.getItems().addAll(
            "09:00", "09:30", "10:00", "10:30", "11:00", "11:30",
            "12:00", "12:30", "13:00", "13:30", "16:00", "16:30",
            "17:00", "17:30", "18:00", "18:30", "19:00"
        );
        cmbHoraCita.setPromptText("Hora");
        fechaHoraContainer.getChildren().addAll(dateFechaCita, cmbHoraCita);
        
        // NUEVO: Valor del servicio
        Label lblValorServicio = new Label("Valor del servicio ($):");
        TextField txtValorServicio = new TextField();
        txtValorServicio.setPromptText("Ej: 500.00");
        
        // NUEVO: Descripción del servicio
        Label lblDescripcionServicio = new Label("Descripción del servicio:");
        TextArea txtDescripcionServicio = new TextArea();
        txtDescripcionServicio.setPromptText("Ej: Consulta general con análisis de sangre");
        txtDescripcionServicio.setPrefRowCount(3);
        
        // Añadir todos los campos al grid
        int row = 0;
        grid.add(lblMotivo, 0, row);
        grid.add(cmbMotivo, 1, row++);
        
        // Añadir el nuevo campo de estado de cita
        grid.add(lblEstadoCita, 0, row);
        grid.add(cmbEstadoCita, 1, row++);
        
        grid.add(lblSintomas, 0, row);
        grid.add(txtSintomas, 1, row++);
        
        grid.add(lblMedicamentos, 0, row);
        grid.add(txtMedicamentos, 1, row++);
        
        grid.add(lblVeterinario, 0, row);
        grid.add(cmbVeterinario, 1, row++);
        
        grid.add(lblFechaHora, 0, row);
        grid.add(fechaHoraContainer, 1, row++);
        
        // Añadir el campo de valor del servicio
        grid.add(lblValorServicio, 0, row);
        grid.add(txtValorServicio, 1, row++);
        
        // Añadir el campo de descripción del servicio
        grid.add(lblDescripcionServicio, 0, row);
        grid.add(txtDescripcionServicio, 1, row++);
        
        // Estilos para las etiquetas
        grid.getChildren().filtered(node -> node instanceof Label).forEach(node -> 
            ((Label)node).setFont(Font.font("Arial", FontWeight.BOLD, 14))
        );
        
        return grid;
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}