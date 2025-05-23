package com.tienda_fs.tienda_fs.service;

import com.tienda_fs.tienda_fs.model.Envio;
import com.tienda_fs.tienda_fs.repository.EnvioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnvioServiceImpl implements  EnvioService {

    private final EnvioRepository envioRepository;

    @Autowired
    public EnvioServiceImpl(EnvioRepository envioRepository) {
        this.envioRepository = envioRepository;
    }

    @Override
    public List<Envio> obtenerTodos(){
        return envioRepository.obtenerTodos();
    }

    @Override
    public Optional<Envio> obtenerPorId(Long id){
        return envioRepository.buscarPorId(id);
    }

    @Override
    public List<Envio> obtenerPorPedidoId(Long pedidoId){
        return envioRepository.buscarPorPedidoId(pedidoId);
    }
    @Override
    public Envio crearEnvio(Envio envio){
        return envioRepository.save(envio);
    }

    @Override
    public Optional<Envio> actualizarEnvio(Long id, Envio envio){
        return envioRepository.buscarPorId(id).map(existing ->{
            existing.setDireccion(envio.getDireccion());
            existing.setEstado(envio.getEstado());
            return envioRepository.save(existing);
        });
    }

    @Override
    public boolean eliminarEnvio(Long id){
        return envioRepository.buscarPorId(id).map(envio -> {
            envioRepository.delete(envio);
            return true;
        }).orElse(false);
    }
}
