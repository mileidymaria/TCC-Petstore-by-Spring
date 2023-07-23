package org.csu.mypetstore.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("org.csu.mypetstore.repository.mapper") // Substitua "seu.pacote.mapper" pelo pacote onde estão suas interfaces de mappers
public class MyBatisConfig {
    // Configurações adicionais do iBatis, se necessário
}