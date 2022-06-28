//package savvycom.productservice.client;
//
//import savvycom.productservice.config.FeignClientConfig;
//import savvycom.productservice.domain.message.ExtendedMessage;
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.web.bind.annotation.PostMapping;
//
//@FeignClient(name = "auth", configuration = FeignClientConfig.class)
//public interface AuthClient {
//    @PostMapping("/api/auth/user")
//    ExtendedMessage<?> validateToken();
//}
