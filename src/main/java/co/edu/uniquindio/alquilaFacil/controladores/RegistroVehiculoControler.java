package co.edu.uniquindio.alquilaFacil.controladores;

import co.edu.uniquindio.alquilaFacil.excepciones.AtributoNegativoException;
import co.edu.uniquindio.alquilaFacil.excepciones.AtributoVacioException;
import co.edu.uniquindio.alquilaFacil.excepciones.InformacionRepetidaException;
import co.edu.uniquindio.alquilaFacil.modelo.AlquilaFacil;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class RegistroVehiculoControler {

    @FXML
    private TextField txtPlaca;

    @FXML
    private TextField txtReferencia;

    @FXML
    private TextField txtMarca;

    @FXML
    private TextField txtModelo;

    @FXML
    private TextField txtUrl;

    @FXML
    private TextField txtKm;

    @FXML
    private TextField txtNumPuertas;

    @FXML
    private TextField txtPrecioDia;

    @FXML
    private ComboBox<String> opcionesCaja;

    @FXML
    private Button btnAtras;

    @FXML
    private Button btnGuardar;


    //Uso del Singleton
    private final AlquilaFacil alquilaFacil = AlquilaFacil.getInstance();

    public void registrarVehiculo(ActionEvent actionEvent){

        try {
            alquilaFacil.registrarVehiculo(
                    txtPlaca.getText(),
                    txtReferencia.getText(),
                    txtMarca.getText(),
                    Integer.parseInt(txtModelo.getText()),
                    txtUrl.getText(),
                    Integer.parseInt(txtKm.getText()),
                    Float.parseFloat(txtPrecioDia.getText()),
                    opcionesCaja.getValue().equals("Automática"),
                    Integer.parseInt(txtNumPuertas.getText())

            );

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("El vehículo se ha registrado correctamente");
            alert.setHeaderText(null);
            alert.show();

        } catch (AtributoNegativoException e) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.setHeaderText(null);
            alert.show();

        } catch (NumberFormatException e1){

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Tenga en cuenta que el número de puertas, modelo, precio por día y kilometraje deben ser números enteros");
            alert.setHeaderText(null);
            alert.show();
        } catch (AtributoVacioException | InformacionRepetidaException e) {
            throw new RuntimeException(e);
        }
    }

    public void atras(ActionEvent event){

        Object evt = event.getSource();

        if(evt.equals(btnAtras)){
            alquilaFacil.loadStage("/inicio.fxml", event);
        }
    }

    private void agregarTextoAlComboBox(){
        String textoIngresado = txtReferencia.getText();
    }
}
