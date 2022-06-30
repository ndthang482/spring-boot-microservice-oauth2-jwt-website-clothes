package savvycom.productservice;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@RequiredArgsConstructor
//@EnableEurekaClient
//@EnableFeignClients
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ProductServiceApplication {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);
    }

}
