package com.app.converter.controller;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import com.app.converter.entity.Receiver;

@Controller

public class ConversionController

{

	@GetMapping("/display")

	public String display(@ModelAttribute("records") List<Receiver> items, Model model)

	{

		Set<String> from = new TreeSet<>();

		Set<String> to = new TreeSet<>();

		from.add("USD");

		from.add("YEN");

		from.add("INR");

		to.add("USD");

		to.add("YEN");

		to.add("INR");

		model.addAttribute("from", from);

		model.addAttribute("to", to);

		return "converter";

	}

	@GetMapping("/process")

	public String process(@Valid @ModelAttribute("receiver") Receiver receiver, BindingResult error,
			@RequestParam("from") String from, @RequestParam("to") String to,
			@RequestParam("quantity") BigDecimal quantity, Model model) {

		if (error.hasErrors()) {

			return "redirect:/display";

		}

		Map<String, String> map = null;
		try {
			map = new HashMap<>();

			System.out.println(receiver.toString());

			map.put("from", from);

			map.put("to", to);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Receiver entity = new RestTemplate().getForObject("http://localhost:8000/currency-exchange/from/{from}/to/{to}",
				Receiver.class, map);

		entity.setQuantity(quantity);

		BigDecimal result = entity.getConversionMultiple().multiply(quantity);

		entity.setChangedCurrency(result);

		model.addAttribute("result", entity);

		System.out.println(entity.toString());

		return "currency";

	}

	@RequestMapping("/addValue")

	public String addValue() {

		return "addupdate";

	}

	@RequestMapping("/updateValue")

	public String updateValue(@RequestParam("id") BigDecimal id, @ModelAttribute("records") List<Receiver> list,
			Model model) {
		Receiver fetchedValue = null;
		for (Receiver e : list) {

		if (e.getId() == id.longValue()) {
				fetchedValue = e;
				break;
\			}
		}
		model.addAttribute("receiver", fetchedValue);
		return "addupdate";

	}

	@PostMapping("/postValues")

	public String postValues(@Valid @ModelAttribute("receiver") Receiver receive, BindingResult result, Model model)

	{

		if (result.hasErrors())

		{

			return "addupdate";

		}
		final String uri = "http://localhost:8000/postValues";

		System.out.println(receive.toString());

		RestTemplate template = new RestTemplate();

		ResponseEntity<Receiver> response = template.postForEntity(uri, receive, Receiver.class);

		if (response.getStatusCodeValue() == 201)

		{
			String successMessage = "The values were added successfully!";

			model.addAttribute("msg", successMessage);

			model.addAttribute("receiver", receive);

		}

		else

		{

			model.addAttribute("msg", response.getStatusCodeValue());

		}

		return "created";

	}

	@GetMapping("/getRecords")

	public String getRecords()

	{

		return "record";

	}

	@RequestMapping("/deleteValue")

	public String deleteRecord(@RequestParam("id") Long id, @ModelAttribute("records") List<Receiver> list)

	{

		final String uri = "http://localhost:8000/deleteValue/{id}";

		System.out.println("Delete");

		Receiver fetchedValue = null;

		for (Receiver e : list)

		{

			if (e.getId() == id.longValue())

			{

				fetchedValue = e;

				break;

			}

		}

		System.out.println(fetchedValue.getId());

		Map<String, Long> uriVariables = new HashMap<>();

		uriVariables.put("id", fetchedValue.getId());

		RestTemplate template = new RestTemplate();

		template.delete(uri, uriVariables);

		return "redirect:/getRecords";

	}

	@ModelAttribute

	public void addReceiver(Model model, Receiver receiver)

	{

		receiver = new Receiver();

		model.addAttribute("receiver", receiver);

	}

	@ModelAttribute

	public void addOption(Model model)

	{

		ResponseEntity<Receiver[]> response = new RestTemplate().getForEntity("http://localhost:8000/getAll",
				Receiver[].class);

		List<Receiver> records = Arrays.asList(response.getBody());

		model.addAttribute("records", records);

	}

}
