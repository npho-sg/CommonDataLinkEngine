package com.example.integration.reader.filereader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import com.example.integration.exception.ReadException;
import com.example.integration.layout.filetype.FileType;
import com.example.integration.layout.resolver.DevconfigLayoutResolver;
import com.example.integration.layout.resolver.LayoutResolver;
import com.example.integration.model.Devconfig;

public class InputReader implements Reader {

	public List<Devconfig> read(String filePath) {

		try (BufferedReader br = new BufferedReader(new FileReader(filePath));) {

			Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(br);

			Iterator<CSVRecord> it = records.iterator();
			if (!it.hasNext()) {
				throw new ReadException("ファイルが空です");
			}
			CSVRecord firstRecord = it.next();
			List<String> headerColumn = new ArrayList<>();
			for (String value : firstRecord) {

				headerColumn.add(value);

			}
			LayoutResolver resolver = new DevconfigLayoutResolver();
			FileType filetype = resolver.resolve(headerColumn);

			List<Devconfig> list = new ArrayList<>();

			switch (filetype) {

			case DEVICE_CONFIG_V1:
				createConfig_V1(it, list);
				break;
			case DEVICE_CONFIG_NO_HEADER_V1:
				list.add(new Devconfig(
						firstRecord.get(0),
						firstRecord.get(1),
						firstRecord.get(2)));
				createConfig_V1(it, list);
				break;
			default:
				throw new ReadException("未対応のレイアウト");

			}

			return list;

		} catch (IOException e) {

			throw new ReadException("ファイルがみつかりません", e);
		}

	}

	private void createConfig_V1(Iterator<CSVRecord> it, List<Devconfig> list) {

		while (it.hasNext()) {

			CSVRecord record = it.next();
			list.add(new Devconfig(
					record.get(0),
					record.get(1),
					record.get(2)));

		}
	}
}
