package ru.netology.repository;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
//import org.springframework.jdbc.core.JdbcTemplate;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

@Repository
public class DAORepositoryImpl implements DAORepository {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	//@Autowired
	//private JdbcTemplate jdbcTemplate;

	String scriptFile = "product_name.sql";
	String script = read(scriptFile);

	private static String read(String scriptFileName) {
		try (InputStream is = new ClassPathResource(scriptFileName).getInputStream();
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is))) {
			return bufferedReader.lines().collect(Collectors.joining("\n"));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public List<String> getProductName(String name) throws SQLException {
		ConcurrentMap<String, Object> params = new ConcurrentHashMap<>();
		params.put("firstName", name);
		List<String> products = namedParameterJdbcTemplate.query(
				script,
				params,
				(rs, rowNum) -> {
					String productName = rs.getString("product_name");
					return productName;
				});
		return products;
	}
}
