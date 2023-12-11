// package com.duvi.myessen.infra;

// import com.duvi.myessen.controller.exception.food.FoodNotFoundException;
// import com.duvi.myessen.domain.food.Food;
// import com.duvi.myessen.repository.FoodGateway;
// import com.fasterxml.jackson.core.exc.StreamReadException;
// import com.fasterxml.jackson.databind.DatabindException;
// import com.fasterxml.jackson.databind.JsonNode;
// import com.fasterxml.jackson.databind.ObjectMapper;
// import io.github.cdimascio.dotenv.Dotenv;
// import java.io.IOException;
// import java.net.URI;
// import java.util.Optional;

// import org.springframework.http.ResponseEntity;
// import org.springframework.stereotype.Repository;

// import org.springframework.web.util.UriComponentsBuilder;
// import org.springframework.http.HttpHeaders;


// import java.util.HashMap;
// import java.util.Map;

// @Repository
// public class FoodEdamam  implements FoodGateway {

//     //importing dotenv to use .env variables
//     private static Dotenv dotenv = Dotenv.configure().directory("./server").load();
//     //using .env variables
//     private static String APP_ID = "33252eb3";
//     private static String API_KEY = "f5148fc312d4430ece92f86f9ae27e01";
//     private static String API_URL = dotenv.get("API_URL");
//     //query params
    
    
    


// //     // private WebClient client = WebClient.create();
// //     //parsing response to Food object using Jackson
// //     private static Optional<Food> parseResults(byte[] results) {
// //         Food food = new Food();
// //         Optional<Food> opt = Optional.ofNullable(food);
// //         ObjectMapper mapper = new ObjectMapper();
// //         Map<String, String> foodData = new HashMap<String, String>();
// //         //handling exceptions
// //         try { 
// //         JsonNode info = mapper.readValue(results, JsonNode.class);
// //         JsonNode parsedInfo = info.get("parsed").get(0);
// //         if (parsedInfo != null) {
// //             JsonNode foodInfo = parsedInfo.get("food");
// //             JsonNode nutrientInfo = foodInfo.get("nutrients");
// //             String foodName = foodInfo.get("label").asText();
// //             String foodKcal = nutrientInfo.get("ENERC_KCAL").asText();
// //             foodData.put("name", foodName);
// //             foodData.put("kcal", foodKcal);
// //             food.setName(foodName);
// //             food.setKcal(Long.parseLong(foodKcal));
// //         } else {
// //             return Optional.empty();
// //         }
// //         } catch (StreamReadException exception ){
// //             exception.printStackTrace();
// //         } catch (DatabindException exception ) {
// //             exception.printStackTrace();
// //         } catch (IOException exception ) {
// //             exception.printStackTrace();
// //         }
// //         return opt;
// // } 
//     public Optional<Food> getFoodByName(String name) throws FoodNotFoundException {
//         // System.out.print(APP_ID+" "+API_KEY);
//         // String query = "?app_id="+APP_ID+"&api_key="+API_KEY+"&ingr="+name;
//         // UriComponentsBuilder builder = UriComponentsBuilder
//         // .fromUriString(API_URL)
//         // .queryParam("app_id", APP_ID)
//         // .queryParam("api_key",API_KEY)
//         // .queryParam("ingr", name);
//         // URI uri = builder.build().toUri();
//         // HttpHeaders headers = new HttpHeaders();
//         // headers.add("Content-Type", "application/json");
//         // ResponseEntity<byte[]> response = client.get().uri(uri).headers((f) -> f.addAll(headers)).retrieve().toEntity(byte[].class).block();
//         // byte[] body = response.getBody();
//         // Optional<Food> food = parseResults(body);
//         // if (food.isPresent()) {
//         //     return food;
//         // } else {
//         //     return Optional.empty();
//         // }
//         Food f = new Food();
//         Optional<Food> opt = Optional.ofNullable(f);
//         return opt;
//     };
// }