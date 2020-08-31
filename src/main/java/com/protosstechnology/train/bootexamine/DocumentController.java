package com.protosstechnology.train.bootexamine;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class DocumentController {
	
	@Autowired
	DocumentRepo repo;

	@PostMapping("/Document")
	public DocumentResponse createNewDocument(@RequestBody DocumentRequest request) {

		Document doc = new Document();
			doc.setDocumentNumber(request.getDocumentNumber());
			doc.setDescription(request.getDescription());
			doc = repo.save(doc);
			return new DocumentResponse(doc.getId(),doc.getDocumentNumber()+doc.getDescription());
	
	}
	
	@GetMapping("/Document")
	public PagingResponse getAllDocument(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(name = "item_per_page", defaultValue = "10") int itemPerPage) {

        PagingResponse pagingResponse = new PagingResponse(page, itemPerPage);
        List<DocumentResponse> docResponseList = new ArrayList<>();

        Iterable<Document> docs = repo.findAll();
        for (Document doc : docs) {
        	docResponseList.add(new DocumentResponse(doc.getId(), doc.getDocumentNumber(), doc.getDescription()));
        }

        pagingResponse.setdocResponse(docResponseList);
        return pagingResponse;
	}
	@GetMapping("/Document/{id}")
    public DocumentResponse getDocumentById(@PathVariable Long id) {
        Optional<Document> doc = repo.findById(id);
        return new DocumentResponse(doc.get().getId(),doc.get().getDocumentNumber());
    }
	@DeleteMapping("/Document/{id}")
	public String deleteDocumentById(@PathVariable Long id) {
        Optional<Document> doc = repo.findById(id);
        if(doc.isPresent()) {
        	repo.delete(doc.get());
        	return "Document is deleted with id "+id;
        }else {
        	throw new RuntimeException("Document not found for the id "+id);
        }

    }
}
