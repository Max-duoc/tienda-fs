package com.tienda_fs.tienda_fs.service;

import com.tienda_fs.tienda_fs.model.Pedido;
import com.tienda_fs.tienda_fs.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository pedidoRepository;

    @Autowired
    public PedidoServiceImpl(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    @Override
    public Optional<Pedido> obtenerPorId(Long id){
        return pedidoRepository.obtenerPorId(id);
    }

    @Override
    public List<Pedido> obtenerTodos(){
        return pedidoRepository.obtenerTodos();
    }

    @Override
    public List<Pedido> obtenerPorUsuarioId(Long id){
        return pedidoRepository.obtenerPorUsuarioId(id);
    }

    @Override
    public Pedido crearPedido(Pedido pedido){
        return pedidoRepository.save(pedido);
    }

    @Override
    public Optional<Pedido> actualizarPedido(Long id, Pedido pedido){
        return pedidoRepository.obtenerPorId(id).map(existing ->{
            existing.setUsuario(pedido.getUsuario());
            existing.setPrecio(pedido.getPrecio());
            existing.setProductos(pedido.getProductos());
            return pedidoRepository.save(existing);
        });
    }

    @Override
    public boolean eliminarPedido(Long id){
        return pedidoRepository.obtenerPorId(id).map(pedido -> {
            pedidoRepository.deleteById(id);
            return true;
        }).orElse(false);
    }


}
