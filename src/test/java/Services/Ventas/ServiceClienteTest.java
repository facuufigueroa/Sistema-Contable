package Services.Ventas;
import Model.Ventas.Persona;
import Model.Ventas.TablaPersona;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class ServiceClienteTest extends ServiceCliente{

    @Test
    void testGetPersonaByEmail() {
        String email = "empresa@gmail.com";
        Persona persona = getPersonaByEmail(email);
        assertEquals("empresa@gmail.com", persona.getEmail());
    }

    @Test
    void testInsertarPersona() {
        Long dni = 41203108L;
        Persona persona = new Persona(dni, "20412031085", "Fernando", "Andana"
                            , "fenando@gmail.com", "Simon Bolivar 25", "2364111111", null
                            , true
        );
        assertTrue(insertarPersona(persona), "No se registro la persona");
    }

    @Test
    void testListadoPersona() {
        ArrayList<TablaPersona> personas = listadoPersona();
        assertEquals(2, personas.size());
    }
}