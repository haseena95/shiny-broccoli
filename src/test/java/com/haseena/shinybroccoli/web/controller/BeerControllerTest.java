package com.haseena.shinybroccoli.web.controller;



//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.put;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.restdocs.snippet.Attributes.key;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.constraints.ConstraintDescriptions;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.haseena.shinybroccoli.repositories.BeerRepository;
import com.haseena.shinybroccoli.web.model.BeerDto;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.haseena.shinybroccoli.web.model.BeerStyleEnum;
import com.haseena.shinybroccoli.web.service.BeerService;

@ExtendWith(RestDocumentationExtension.class)
@AutoConfigureRestDocs(uriScheme = "https", uriHost = "dev.springframework.guru",uriPort = 80)

@WebMvcTest(BeerController.class)
class BeerControllerTest {

	@Autowired
	MockMvc mockMvc;
	@Autowired
	ObjectMapper objectMapper;
	
	@MockBean
	BeerService beerService;
	@MockBean
	BeerRepository beerRepository;
	
	@Test
	void getBeerById() throws Exception {
		
		given(beerService.getBeerById(any())).willReturn(getValidBeerDto());
	
		mockMvc.perform(get("/api/v1/beer/{beerId}",UUID.randomUUID().toString())
				.param("iscold","yes")
				.accept(MediaType.APPLICATION_JSON_VALUE))	
		  .andExpect(MockMvcResultMatchers.status().isOk())
		  .andDo(document("v1/beer",
				 pathParameters(parameterWithName("beerId")
				  					      .description("UUID of the beer to get.")
							), 
				  requestParameters(parameterWithName("iscold")
			  					      .description("Is beer cold query param")
			   		       ),
				  responseFields(
						  fieldWithPath("id").description("Id of Beer"),
						  fieldWithPath("version").description("Version number"),
						  fieldWithPath("createdDate").description("Date Created"),
						  fieldWithPath("lastModifiedDate").description("Date Updated"),
						  fieldWithPath("beerName").description("Name of the Beer"),
						  fieldWithPath("beerStyle").description("Beer Style"),
						  fieldWithPath("upc").description("UPC of Beer"),
						  fieldWithPath("price").description("Price"),
						  fieldWithPath("quantityOnHand").description("Quantity On Hand")
						  )
				            ));
	}
	
	@Test
	void saveNewBeer() throws Exception {
		BeerDto beerDto = getValidBeerDto();
		String beerDtoJson= objectMapper.writeValueAsString(beerDto);
		
		given(beerService.saveNewBeer(any())).willReturn(getValidBeerDto());
		
		ConstrainedFields fields = new  ConstrainedFields(BeerDto.class);
		
		mockMvc.perform(post("/api/v1/beer/")
						.contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(beerDtoJson))
						.andExpect(MockMvcResultMatchers.status().isCreated())
						.andDo(document("v1/beer",
								requestFields(
										  fields.withPath("id").ignored(),
										  fields.withPath("version").ignored(),
										  fields.withPath("createdDate").ignored(),
										  fields.withPath("lastModifiedDate").ignored(),
										  fields.withPath("beerName").description("Name of the Beer"),
										  fields.withPath("beerStyle").description("Beer Style"),
										  fields.withPath("upc").description("UPC of Beer").attributes(),
										  fields.withPath("price").description("Beer Price"),
										  fields.withPath("quantityOnHand").ignored()
										  
								)));
	}
	
	@Test
	void updateBeerByID() throws Exception {
		BeerDto beerDto = getValidBeerDto();
		String beerDtoJson= objectMapper.writeValueAsString(beerDto);
		
		given(beerService.updateBeer(any(),any())).willReturn(getValidBeerDto());
		mockMvc.perform(put("/api/v1/beer/{beerId}",beerDto.getId().toString())
						.contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(beerDtoJson))
						.andExpect(MockMvcResultMatchers.status().isNoContent());
		
		
	}
	
	BeerDto getValidBeerDto() {
		return BeerDto.builder().id(UUID.randomUUID()).beerName("Sonu Beer").beerStyle(BeerStyleEnum.CC).price(new BigDecimal("3.99"))
				.upc(4888900000L).createdDate(OffsetDateTime.now()).lastModifiedDate(OffsetDateTime.now()).quantityOnHand(20).version(2).build();
	}
	
	private static class ConstrainedFields {
		private final  ConstraintDescriptions constraintDescriptions; 
		
		ConstrainedFields(Class<?> input){
			this.constraintDescriptions = new ConstraintDescriptions(input);
		}
		private FieldDescriptor withPath(String path) {
			return fieldWithPath(path).attributes(key("constraints").value(StringUtils.collectionToDelimitedString(
					this.constraintDescriptions.descriptionsForProperty(path),". ")));
			
		}
		
		
	}

}
