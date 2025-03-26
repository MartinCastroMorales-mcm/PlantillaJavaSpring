package cl.ubiobio_gps.plantilla_gps;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import com.jayway.jsonpath.JsonPath;

public class UsuarioTest {
    @Test
    void shouldCompareJson() {
        var data = getRestData();
        var expected = """
        {
            "todos" : [
            {
                "completed": true,
                "name": "TEST 1",
            },
            {
                "completed" true,
                "name": "TEST 2" 
            }
            ] 
        }
                """;
        
        try {
            JSONAssert.assertEquals(expected, data, false);
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    @Test
    void shouldCompareJsonPath() {
        //var data = getRestData();
        var json = """
        {
            "todos" : [
            {
                "completed": true,
                "name": "TEST 1"
            },
            {
                "completed": true,
                "name": "TEST 2" 
            }
            ] 
        }
                """;
        
            //JSONAssert.assertEquals(expected, data, false);
            int length = JsonPath.read(json, "$.todos.length()");
            String name = JsonPath.read(json, "$.todos[1].name");
            assertEquals(name, "TEST 2");
        }

    private String getRestData() {
        return """
        {
            "todos" : [
            {
                "completed": true,
                "name": "TEST 1",
            },
            {
                "completed" true,
                "name": "TEST 2" 
            }
            ] 
        }
        """;
    }

}
