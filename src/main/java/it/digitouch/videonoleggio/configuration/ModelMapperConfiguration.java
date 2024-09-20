package it.digitouch.videonoleggio.configuration;


import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

 /***************************************************************************************************************************************
 *		<dependency>                                                                                                                    *
 *			<groupId>org.modelmapper</groupId>                                                                                          *
 *			<artifactId>modelmapper</artifactId>                                                                                        *
 *			<version>3.2.1</version>                                                                                                    *
 *		</dependency>                                                                                                                   *
  ***************************************************************************************************************************************
  *   GRAZIE ALL' ESTENZIONE MODLMAPPER io in configuration creo il modelmapperconfiguration                                            *
  *   creo il metodo @Bean modelMapper che ritorna ModelMapper() per poter convertire i dati primo parametro nel secondo parametro      *
  * ES vedi FilmService                                                                                                                 *
  ***************************************************************************************************************************************/

@Configuration

public class ModelMapperConfiguration {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
