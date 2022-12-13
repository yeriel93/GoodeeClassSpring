package com.property.model.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Files {
	private int filesNo;
	private int propertyNo;
	private String renameFilename;
	private char thumbnail;
}
