package com.protosstechnology.train.bootexamine;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.awt.print.Book;
import java.util.Optional;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@RunWith(SpringRunner.class)
@WebMvcTest(controllers = DocumentController.class)
class BootexamineApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private DocumentRepo repo;

	@Test
	public void GetBookById() throws Exception {
		// Arrange
		Document doc = new Document(1l, "Dummy Doc", "Dummy Doc");
		given(repo.findById(1l)).willReturn(Optional.of(doc));

		// Act and Assert
		mockMvc.perform(get("/Document/1")).andExpect(jsonPath("$.description").value("Dummy Doc"))
				.andExpect(status().is(200));
	}

	@Test
	public void GetBookById2() throws Exception {
		mockMvc.perform(get("/Document/10")).andExpect(jsonPath("$.description").value("New book")).andExpect(status().is(200));
	}
	
	@Test
    public void success() {
        // Arrange
		Document doc = new Document(1l, "Dummy Doc", "Dummy Doc");
        repo.save(doc);
        // Act
        Optional<Document> actualDoc = repo.findById(1l);
        // Assert
        assertTrue(actualDoc.isPresent());
        assertEquals("Dummy Doc", actualDoc.get().getDescription());
    }
	
}
