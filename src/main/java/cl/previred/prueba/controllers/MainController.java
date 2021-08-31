package cl.previred.prueba.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.previred.prueba.model.ResponseData;
import cl.previred.prueba.services.SearchMissingsDateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController()
@Api(tags = "", value = "")
@CrossOrigin(origins = "*")
public class MainController {
    
    @Autowired
    private SearchMissingsDateService service;
    
    @ApiOperation(value = "getData", notes = "")
    @ApiResponses({
	    @ApiResponse(code = 200, message = "", response = ResponseData.class),
	    @ApiResponse(code = 401, message = "", response = HttpStatus.class) })
    @PostMapping(value = "/data")
    public ResponseEntity<ResponseData> getData(){
	return new ResponseEntity<>(service.getData(),HttpStatus.OK);
    }
    
    @ApiOperation(value = "healthcheck", notes = "")
    @ApiResponses({
	    @ApiResponse(code = 200, message = "", response = String.class)})
    @GetMapping(value = "/healthcheck")
    public ResponseEntity<String> isAlive(){
	return new ResponseEntity<>("ok",HttpStatus.OK);
    }
    
}
