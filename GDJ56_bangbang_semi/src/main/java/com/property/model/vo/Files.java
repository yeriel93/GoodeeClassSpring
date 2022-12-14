package com.property.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Files {
	private int filesNo;
	private int propertyNo;
	private String renameFilename;
	private char thumbnail;
}
