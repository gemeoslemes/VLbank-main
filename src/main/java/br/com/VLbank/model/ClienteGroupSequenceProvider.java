//package br.com.VLbank.model;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;
//
//import br.com.VLbank.DTO.ClienteDto;
//
//public class ClienteGroupSequenceProvider implements DefaultGroupSequenceProvider<Cliente>{
//
////	@Override
////	public List<Class<?>> getValidationGroups(Cliente cliente) {
////		List<Class<?>> groups = new ArrayList<>();
////		groups.add(Cliente.class);
////		if(isPessoaSelecionada(cliente)) {
////			groups.add(cliente.getTipo());
////		}
////		return groups;
////	}
////	
////	private boolean isPessoaSelecionada(Cliente cliente) {
////		return cliente != null && cliente.getTipo() != null;
////	}
////
////	protected boolean isPessoaSelecionada(ClienteDto cliente) {
////		return cliente != null && cliente.getTipo() != null;
////	}
//
//}
