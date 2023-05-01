package org.alexsandrov.spring.database.repository;

import org.alexsandrov.spring.bpp.InjectBean;
import org.alexsandrov.spring.database.pool.ConnectionPool;

public class CompanyRepository {
    @InjectBean
    private ConnectionPool connectionPool;
}
