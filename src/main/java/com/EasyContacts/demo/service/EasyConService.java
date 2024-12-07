package com.EasyContacts.demo.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class EasyConService {

    private final JdbcTemplate jdbcTemplate;

    public EasyConService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public String getTotalContatosCom11() {
        String sql = "SELECT total_contatos_com_11 FROM view_contatos_com_11";
        Integer total = jdbcTemplate.queryForObject(sql, Integer.class);
        return total != null ? total.toString() : "0";
    }

    public String contarTotalContatos() {
        String sql = "CALL contar_contatos()";
        return jdbcTemplate.query(sql, (rs) -> {
            if (rs.next()) {
                int total = rs.getInt("total_contatos");
                return String.valueOf(total);
            }
            return "0";
        });
    }
}
