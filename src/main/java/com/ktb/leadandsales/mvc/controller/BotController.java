package com.ktb.leadandsales.mvc.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.yaml.snakeyaml.Yaml;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.objectmapper.ModelObjectMapper;
import com.linecorp.bot.model.response.BotApiResponse;
import com.linecorp.bot.model.richmenu.RichMenu;
import com.linecorp.bot.model.richmenu.RichMenuIdResponse;
import com.linecorp.bot.model.richmenu.RichMenuListResponse;
import com.linecorp.bot.model.richmenu.RichMenuResponse;
import com.fasterxml.jackson.core.JsonParser.Feature;

import lombok.extern.slf4j.Slf4j;

//@ComponentScan(basePackages = "com.example")
@Slf4j
//@LineMessageHandler
public class BotController {
	private static final Logger log = LoggerFactory.getLogger(BotController.class);
    
//    private static String token = "xxxx"; // Your LINE token
//    private static LineMessagingClient lineMessagingClient = 
//                            LineMessagingClient.builder(token).build();
//
//    public static void main(String[] args) throws IOException {
//        // YAML
//        String pathYamlHome = "asset/richmenu-home.yml";
//        String pathYamlMore = "asset/richmenu-more.yml";
//        // Rich Image Menu
//        String pathImageHome = "asset/richmenu-home.jpg";
//        String pathImageMore = "asset/richmenu-more.jpg";
//      
//        String richMenuId;
//
//        // Create 1st Rich Menu (Home Menu)
//        richMenuId = createRichMenu(pathYamlHome);
//        imageUploadRichMenu(richMenuId, pathImageHome);
//
//        // Create 2nd Rich Menu (More Menu)
//        richMenuId = createRichMenu(pathYamlMore);
//        imageUploadRichMenu(richMenuId, pathImageMore);
//
//        listRichMenu(); // Show created Rich Menus
//
//    }
//
//    private static String createRichMenu(String path) throws IOException {
//        RichMenu richMenu = loadYaml(path);
//        log.info("{}", richMenu);
//
//        RichMenuIdResponse richMenuResponse = getUnchecked(
//                                    lineMessagingClient.createRichMenu(richMenu));
//        log.info("Successfully finished.");
//        log.info("{}", richMenuResponse);
//        return richMenuResponse.getRichMenuId();
//    }
//
//    private static void imageUploadRichMenu(String richMenuId, String path) throws IOException {
//        String contentType = getDefaultFileTypeMap().getContentType(path);
//        log.info("Content-Type: {}", contentType);
//
//        byte[] bytes = Files.readAllBytes(Paths.get(path));
//
//        BotApiResponse botApiResponse = getUnchecked(
//                                    lineMessagingClient.setRichMenuImage(
//                                        richMenuId, contentType, bytes));
//        log.info("Successfully finished.");
//        log.info("{}", botApiResponse);
//    }
//  
//    private static List<String> listRichMenu() {
//        List<String> listMenuString = new ArrayList<>();
//
//        RichMenuListResponse richMenuListResponse = getUnchecked(
//                                    lineMessagingClient.getRichMenuList());
//        List<RichMenuResponse> listMenus = richMenuListResponse.getRichMenus();
//        log.info("You have {} RichMenus", listMenus.size());
//
//        log.info("Successfully finished.");
//        listMenus.forEach(richMenuResponse -> {
//            listMenuString.add(richMenuResponse.getRichMenuId());
//            log.info("{}", richMenuResponse);
//        });
//
//        return listMenuString;
//    }
//
//    private static RichMenu loadYaml(String path) throws IOException {
//        final Yaml YAML = new Yaml();
//        final ObjectMapper OBJECT_MAPPER = ModelObjectMapper
//                .createNewObjectMapper()
//                .configure(Feature.ALLOW_UNQUOTED_FIELD_NAMES, true)
//                .configure(Feature.ALLOW_COMMENTS, true)
//                .configure(Feature.ALLOW_SINGLE_QUOTES, true);
//
//        Object yamlAsObject;
//        try(FileInputStream is = new FileInputStream(path)) {
//            yamlAsObject = YAML.load(is);
//        }
//
//        return OBJECT_MAPPER.convertValue(yamlAsObject, RichMenu.class);
//    }
}
