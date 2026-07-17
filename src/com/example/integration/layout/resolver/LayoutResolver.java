package com.example.integration.layout.resolver;

import java.util.List;

import com.example.integration.layout.filetype.FileType;

public interface LayoutResolver {
	
	FileType resolve(List<String> headers);

}
