package com.protosstechnology.train.bootexamine;

import lombok.Data;

@Data
public class DocumentResponse {

	private Long id;
	private String documentNumber;
	private String description;

	public DocumentResponse(Long id, String documentNumber) {
        this(id, documentNumber, "");
    }
	public DocumentResponse(Long id, String documentNumber, String description) {
		super();
		this.id = id;
		this.documentNumber = documentNumber;
		this.description = description;
	}
	
}
