package comandos;

import mensajeria.Comando;

public class RegistroSet extends ComandosCliente {

	@Override
	public void ejecutar() {
		cliente.getPaqueteUsuario().setComando(Comando.REGISTRO);
	}

}
