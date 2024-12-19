package com.jb3bma.bartfaiattila.Kontaktmanager_sppti.Kontaktmanager_sppti;

import com.jb3bma.bartfaiattila.Kontaktmanager_sppti.Kontaktmanager_sppti.NevjegyController;
import com.jb3bma.bartfaiattila.Kontaktmanager_sppti.Kontaktmanager_sppti.NevjegyRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;




@WebMvcTest(NevjegyController.class)
@AutoConfigureMockMvc(addFilters = false) // Kikapcsolom a security szűrő feltételt
// @Import(SecurityConfig.class)             // Ha esetleg be kell tölteni a security configot
public class NevjegyControllerTest {

	@MockBean
	private UserDetailsService userDetailsService;
	
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NevjegyRepository nevjegyRepository;

    @Test
    public void contextLoads() {
        // A környezet ellenőrzését végzi
    }
    
    
    	@Test // Névjegyek lekérdezése
    	public void testGetAllNevjegyek() throws Exception {
    
    		Nevjegy nevjegy1 = new Nevjegy();
    		nevjegy1.setAzonosito(1);
    		nevjegy1.setVezeteknev("Teszt");
    		nevjegy1.setKeresztnev("Elek");

    		Nevjegy nevjegy2 = new Nevjegy();
    		nevjegy2.setAzonosito(2);
    		nevjegy2.setVezeteknev("Nagy");
    		nevjegy2.setKeresztnev("Anna");

    		// Mock adatok beállítása
    		Mockito.when(nevjegyRepository.findAll()).thenReturn(List.of(nevjegy1, nevjegy2));

    		// MockMvc GET kérés és ellenőrzés
    		String responseContent = mockMvc.perform(MockMvcRequestBuilders.get("/nevjegyek"))
        	
                .andExpect(MockMvcResultMatchers.status().isOk()) // HTTP 200 OK
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].vezeteknev").value("Teszt"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].vezeteknev").value("Nagy"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2))
                .andReturn()
                .getResponse()
                .getContentAsString();

            // A válasz log, hogy lássam a tartalmat
        System.out.println("! TESZT ! - névjegy lekérdezés eredménye: " + responseContent);
     }

    	@Test // Névjegy létrehozása
    	public void testAddNevjegy() throws Exception {
    	   
    	    Nevjegy newNevjegy = new Nevjegy();
    	    newNevjegy.setAzonosito(3);
    	    newNevjegy.setVezeteknev("Teszt");
    	    newNevjegy.setKeresztnev("Elek");

    	    // Mock repository mentésre adott válasz beállítása
    	    Mockito.when(nevjegyRepository.save(Mockito.any(Nevjegy.class))).thenReturn(newNevjegy);

    	    // POST kérés létrehozása a MockMvc segítségével
    	    String responseContent = mockMvc.perform(MockMvcRequestBuilders.post("/nevjegyek")
    	            .contentType("application/json") // JSON formátum beállítása
    	            .content("{\"azonosito\": 3, \"vezeteknev\": \"Teszt\", \"keresztnev\": \"Elek\"}")) // Tesztadat JSON
    	            .andExpect(MockMvcResultMatchers.status().isCreated()) // Ellenőrizze a 201 CREATED státuszt
    	            .andExpect(MockMvcResultMatchers.jsonPath("$.vezeteknev").value("Teszt")) // Ellenőrizze a vezetéknév értékét
    	            .andExpect(MockMvcResultMatchers.jsonPath("$.keresztnev").value("Elek")) // Ellenőrizze a keresztnév értékét
    	            .andReturn()
    	            .getResponse()
    	            .getContentAsString();

            // A válasz log, hogy lássam a tartalmat
    	    System.out.println("! TESZT ! - névjegy létrehozás eredménye: " + responseContent);

    	    // repository mentési művelet egyszer meghívva
    	    Mockito.verify(nevjegyRepository, Mockito.times(1)).save(Mockito.any(Nevjegy.class));
    	}

    	@Test // Névjegy törlése
    	public void testDeleteNevjegy() throws Exception {
    	  
    	    Nevjegy nevjegyToDelete = new Nevjegy();
    	    nevjegyToDelete.setAzonosito(3);
    	    nevjegyToDelete.setVezeteknev("Teszt");
    	    nevjegyToDelete.setKeresztnev("Elek");

    	    Mockito.when(nevjegyRepository.existsById(3)).thenReturn(true);

    	    // Mock repository törlés
    	    Mockito.doNothing().when(nevjegyRepository).deleteById(3);

    	    // DELETE kérés végrehajtása
    	    mockMvc.perform(MockMvcRequestBuilders.delete("/nevjegyek/3"))
    	            .andExpect(MockMvcResultMatchers.status().isNoContent()); // Ellenőrizze, hogy a státusz 204 NO CONTENT

    	    // Ellenőrizzük, hogy a törlés végrehajtódott-e
    	    Mockito.verify(nevjegyRepository, Mockito.times(1)).deleteById(3);

            // A válasz log, hogy lássam a tartalmat
    	    System.out.println("! TESZT ! - névjegy törlés eredménye: A tesztelt 3-as azonosítójú sikeresen törölve.");
    	}
    	
    	@Test // Névjegy frissítése
    	public void testUpdateNevjegy() throws Exception {
    	    
    	    Nevjegy existingNevjegy = new Nevjegy();
    	    existingNevjegy.setAzonosito(2);
    	    existingNevjegy.setVezeteknev("Nagy");
    	    existingNevjegy.setKeresztnev("Anna");

    	    Nevjegy updatedNevjegy = new Nevjegy();
    	    updatedNevjegy.setAzonosito(2);
    	    updatedNevjegy.setVezeteknev("Módosított");
    	    updatedNevjegy.setKeresztnev("Adat");

    	    // Mock repository adatok
    	    Mockito.when(nevjegyRepository.findById(2)).thenReturn(java.util.Optional.of(existingNevjegy));
    	    Mockito.when(nevjegyRepository.save(Mockito.any(Nevjegy.class))).thenReturn(updatedNevjegy);

    	    // PUT kérés létrehozása MockMvc segítségével
    	    String responseContent = mockMvc.perform(MockMvcRequestBuilders.put("/nevjegyek/2")
    	            .contentType("application/json")
    	            .content("{\"azonosito\": 2, \"vezeteknev\": \"Módosított\", \"keresztnev\": \"Adat\"}"))
    	            .andExpect(MockMvcResultMatchers.status().isOk()) // HTTP 200 OK
    	            .andExpect(MockMvcResultMatchers.jsonPath("$.vezeteknev").value("Módosított")) // Ellenőrizze a vezetéknév módosítását
    	            .andExpect(MockMvcResultMatchers.jsonPath("$.keresztnev").value("Adat")) // Ellenőrizze a keresztnév módosítását
    	            .andReturn()
    	            .getResponse()
    	            .getContentAsString();

            // A válasz log, hogy lássam a tartalmat
    	    System.out.println("! TESZT ! - névjegy frissítés eredménye: " + responseContent);

    	    // Ellenőrizze, hogy a mentési művelet egyszer megtörtént
    	    Mockito.verify(nevjegyRepository, Mockito.times(1)).save(Mockito.any(Nevjegy.class));
    	}
    	
    	@Test // ID alapján névjegy lekérdezés
    	public void testGetNevjegyById() throws Exception {
    	   
    	    Nevjegy nevjegy = new Nevjegy();
    	    nevjegy.setAzonosito(1);
    	    nevjegy.setVezeteknev("Kovács");
    	    nevjegy.setKeresztnev("Béla");

    	    // Mock repository válasz beállítása
    	    Mockito.when(nevjegyRepository.findById(1)).thenReturn(java.util.Optional.of(nevjegy));

    	    // GET kérés létrehozása és ellenőrzés
    	    String responseContent = mockMvc.perform(MockMvcRequestBuilders.get("/nevjegyek/1"))
    	            .andExpect(MockMvcResultMatchers.status().isOk()) // HTTP 200 OK
    	            .andExpect(MockMvcResultMatchers.jsonPath("$.vezeteknev").value("Kovács")) // Ellenőrzi a vezetéknév értékét
    	            .andExpect(MockMvcResultMatchers.jsonPath("$.keresztnev").value("Béla")) // Ellenőrzi a keresztnév értékét
    	            .andReturn()
    	            .getResponse()
    	            .getContentAsString();

            // A válasz log, hogy lássam a tartalmat
    	    System.out.println("! TESZT ! - névjegy lekérdezés (ID szerint) eredménye: " + responseContent);

    	    // Ellenőrzi, hogy a repository hívás megtörtént
    	    Mockito.verify(nevjegyRepository, Mockito.times(1)).findById(1);
    	}

    	@Test // Nemlétező ID-vel névjegy lekérdezés
    	public void testGetNevjegyByNonExistentId() throws Exception {
    	    
    	    Mockito.when(nevjegyRepository.findById(99)).thenReturn(java.util.Optional.empty());

    	    // GET kérés nem létező ID-vel
    	    mockMvc.perform(MockMvcRequestBuilders.get("/nevjegyek/99"))
    	            .andExpect(MockMvcResultMatchers.status().isNotFound()) // HTTP 404 Not Found
    	            .andReturn();

    	    // Ellenőrzi, hogy a repository hívás megtörtént
    	    Mockito.verify(nevjegyRepository, Mockito.times(1)).findById(99);

            // A válasz log, hogy lássam a tartalmat
    	    System.out.println("! TESZT ! - névjegy (nem létező) lekérdezés eredménye: Sikeresen lefutott.");
    	}

    	@Test // Teszt nemlétező névjegy frissítés
    	public void testUpdateNevjegyWithNonExistentId() throws Exception {

    	    Nevjegy updatedNevjegy = new Nevjegy();
    	    updatedNevjegy.setAzonosito(99);
    	    updatedNevjegy.setVezeteknev("Frissített");
    	    updatedNevjegy.setKeresztnev("Adat");

    	    // Mock repository válasz beállítása nem létező ID esetén
    	    Mockito.when(nevjegyRepository.findById(99)).thenReturn(java.util.Optional.empty());

    	    // PUT kérés nem létező ID-vel
    	    String responseContent = mockMvc.perform(MockMvcRequestBuilders.put("/nevjegyek/99")
    	            .contentType("application/json")
    	            .content("{\"vezeteknev\": \"Frissített\", \"keresztnev\": \"Adat\"}"))
    	            .andExpect(MockMvcResultMatchers.status().isNotFound()) // Ellenőrizze a 404 státuszt
    	            .andExpect(MockMvcResultMatchers.content().string("Névjegy nem található az adott azonosítóval: 99")) // Ellenőrizze az üzenetet
    	            .andReturn()
    	            .getResponse()
    	            .getContentAsString();

            // A válasz log, hogy lássam a tartalmat
    	    System.out.println("! TESZT ! - névjegy frissítés (nem létező ID) eredménye: " + responseContent);
    	}

    	@Test    // Mock üres adatbázis
    	public void testGetAllNevjegyekWithEmptyDatabase() throws Exception {
    	 
    	    Mockito.when(nevjegyRepository.findAll()).thenReturn(List.of());

    	    // GET kérés és ellenőrzés
    	    String responseContent = mockMvc.perform(MockMvcRequestBuilders.get("/nevjegyek"))
    	            .andExpect(MockMvcResultMatchers.status().isOk()) // HTTP 200 OK
    	            .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(0)) // Üres tömb ellenőrzése
    	            .andReturn()
    	            .getResponse()
    	            .getContentAsString();
            // A válasz log, hogy lássam a tartalmat
    	    System.out.println("! TESZT ! - üres adatbázis lekérdezés eredménye: " + responseContent);
    	}

    	@Test // Mock azonosító nem található
    	public void testDeleteNevjegyWithInvalidId() throws Exception {
    	   
    	    Mockito.when(nevjegyRepository.existsById(99)).thenReturn(false);

    	    // DELETE kérés
    	    mockMvc.perform(MockMvcRequestBuilders.delete("/nevjegyek/99"))
    	            .andExpect(MockMvcResultMatchers.status().isNotFound()); // HTTP 404 Not Found
            // A válasz log, hogy lássam a tartalmat
    	    System.out.println("! TESZT ! - névjegy törlés nem létező ID-vel eredménye: Sikeresen lefutott.");
    	}

    	@Test // POST kérés hibás JSON-nel
    	public void testAddNevjegyWithInvalidFormat() throws Exception {
    	   
    	    mockMvc.perform(MockMvcRequestBuilders.post("/nevjegyek")
    	            .contentType("application/json")
    	            .content("{\"invalidField\": \"value\"}")) // Hibás JSON tartalom
    	            .andExpect(MockMvcResultMatchers.status().isBadRequest()); // HTTP 400 Bad Request
            // A válasz log, hogy lássam a tartalmat
    	    System.out.println("! TESZT ! - hibás Json bemenet eredménye: Sikeresen lefutott.");
    	}

    	@Test // Tesztadat maximális méretű mezőkkel (255 kar.)
    	public void testAddNevjegyWithMaxLengthFields() throws Exception {
    	    
    	    Nevjegy nevjegy = new Nevjegy();
    	    nevjegy.setAzonosito(4);
    	    nevjegy.setVezeteknev("A".repeat(255));
    	    nevjegy.setKeresztnev("B".repeat(255));

    	    // Mock repository mentés
    	    Mockito.when(nevjegyRepository.save(Mockito.any(Nevjegy.class))).thenReturn(nevjegy);

    	    // POST kérés
    	    String responseContent = mockMvc.perform(MockMvcRequestBuilders.post("/nevjegyek")
    	            .contentType("application/json")
    	            .content("{\"azonosito\": 4, \"vezeteknev\": \"" + "A".repeat(255) + "\", \"keresztnev\": \"" + "B".repeat(255) + "\"}"))
    	            .andExpect(MockMvcResultMatchers.status().isCreated())
    	            .andReturn()
    	            .getResponse()
    	            .getContentAsString();
            // A válasz log, hogy lássam a tartalmat
    	    System.out.println("! TESZT ! - max inputméret eredménye: " + responseContent);
    	}

    	@Test // Tesztadat minimális méretű mezőkkel (1 kar.)
    	public void testAddNevjegyWithMinLengthFields() throws Exception {
    	    
    	    Nevjegy nevjegy = new Nevjegy();
    	    nevjegy.setAzonosito(5);
    	    nevjegy.setVezeteknev("A");
    	    nevjegy.setKeresztnev("B");

    	    // Mock repository mentés
    	    Mockito.when(nevjegyRepository.save(Mockito.any(Nevjegy.class))).thenReturn(nevjegy);

    	    // POST kérés
    	    String responseContent = mockMvc.perform(MockMvcRequestBuilders.post("/nevjegyek")
    	            .contentType("application/json")
    	            .content("{\"azonosito\": 5, \"vezeteknev\": \"A\", \"keresztnev\": \"B\"}"))
    	            .andExpect(MockMvcResultMatchers.status().isCreated())
    	            .andReturn()
    	            .getResponse()
    	            .getContentAsString();
            // A válasz log, hogy lássam a tartalmat
    	    System.out.println("! TESZT ! - min inputméret eredménye: " + responseContent);
    	}

    	@Test // Tesztadat üres mezőkkel
    	public void testAddNevjegyWithEmptyFields() throws Exception {
    	    
    	    Nevjegy nevjegy = new Nevjegy();
    	    nevjegy.setAzonosito(6);
    	    nevjegy.setVezeteknev("");
    	    nevjegy.setKeresztnev("");

    	    // Mock repository mentés
    	    Mockito.when(nevjegyRepository.save(Mockito.any(Nevjegy.class))).thenReturn(nevjegy);

    	    // POST kérés
    	    String responseContent = mockMvc.perform(MockMvcRequestBuilders.post("/nevjegyek")
    	            .contentType("application/json")
    	            .content("{\"azonosito\": 6, \"vezeteknev\": \"\", \"keresztnev\": \"\"}"))
    	            .andExpect(MockMvcResultMatchers.status().isCreated())
    	            .andReturn()
    	            .getResponse()
    	            .getContentAsString();
            // A válasz log, hogy lássam a tartalmat
    	    System.out.println("! TESZT ! - üres mezők eredménye: " + responseContent);
    	}

    	
}
