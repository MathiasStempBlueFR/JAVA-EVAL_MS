package com.JAVA_EVAL.JAVA;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class JavaMsApplicationTests {

	@Autowired
	private WebApplicationContext context;
	private MockMvc mvc;

	@BeforeEach
	public void setup() {
		mvc = MockMvcBuilders
				.webAppContextSetup(context)
				.apply(springSecurity())
				.build();
	}

	// Test 1: Vérifier l'accès sécurisé des utilisateurs
	@Test
	void accesNonAuthentifieAuxRessourcesProtegees_reponse403() throws Exception {
		mvc.perform(get("/salary"))
				.andExpect(status().isForbidden());
	}

	// Test 2: Vérifier les restrictions par rôle
	@Test
	@WithMockUser(username = "user", roles = {"ENTREPRISE"})
	void accesParUneEntrepriseAuxRessourcesAdmin_reponse403() throws Exception {
		mvc.perform(post("/user")
						.contentType("application/json")
						.content("""
                    {
                        \"email\": \"test@entreprise.com\",
                        \"password\": \"1234\"
                    }
                """))
				.andExpect(status().isForbidden());
	}

	// Test 3: Vérifier les droits spécifiques à une entreprise
	@Test
	@WithMockUser(username = "user", roles = {"ENTREPRISE"})
	void accesParUneEntrepriseAModificationDeSesRessources_reponse200() throws Exception {
		mvc.perform(put("/salary/1")
						.contentType("application/json")
						.content("""
                    {
                        \"matricule\": \"ABC123\",
                        \"barCode\": \"123456789\"
                    }
                """))
				.andExpect(status().isOk());
	}

	@Test
	@WithMockUser(username = "user", roles = {"ENTREPRISE"})
	void accesParUneEntrepriseAModificationDuneAutreRessource_reponse403() throws Exception {
		mvc.perform(put("/salary/9999")
						.contentType("application/json")
						.content("""
                    {
                        \"matricule\": \"XYZ123\",
                        \"barCode\": \"987654321\"
                    }
                """))
				.andExpect(status().isForbidden());
	}

}
