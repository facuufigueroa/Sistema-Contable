package Services.Ventas;
import Model.Ventas.Cliente;
import Model.Ventas.TablaPersona;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class ServiceClienteTest extends ServiceCliente{

    @Test
    void testGetPersonaByEmail() {
        String email = "empresa@gmail.com";
        Cliente persona = getPersonaByEmail(email);
        assertEquals("empresa@gmail.com", persona.getEmail());
    }

    @Test
    void testInsertarPersona() {
        Long dni = 11111111L;
        Cliente persona = new Cliente(dni, "20111111115", "Leonel", "Messi"
                            , "leo10@gmail.com", "5 de copa", "2364555555", null
                            , true
        );
        assertTrue(insertarPersona(persona), "No se registro la persona");
    }

    @Test
    void testListadoPersona() {
        ArrayList<TablaPersona> personas = listadoPersona();
        assertEquals(3, personas.size());
    }
}