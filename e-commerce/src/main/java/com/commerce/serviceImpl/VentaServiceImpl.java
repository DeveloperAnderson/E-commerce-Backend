package com.commerce.repository;

import com.commerce.DTO.VentaEntityDto;
import com.commerce.entity.ProductoEntity;
import com.commerce.entity.UserEntity;
import com.commerce.entity.VentaEntity;
import com.commerce.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class VentaServiceImpl implements VentaService {

    @Autowired
    private IVentaRepository ventaRepository;
    private final IProductRepository productoRepository;
    private final IUserRepository userRepository;

    public VentaServiceImpl(IVentaRepository ventaRepository, IProductRepository productoRepository, IUserRepository userRepository) {
        this.ventaRepository = ventaRepository;
        this.productoRepository = productoRepository;
        this.userRepository = userRepository;
    }


    public VentaEntityDto registrarVenta(VentaEntityDto ventaDto) {
        System.out.println("ventaDto.getIdProducto() = " + ventaDto.getIdProducto());
        System.out.println("ventaDto.getIdUsuario() = " + ventaDto.getUsername());
        System.out.println("ventaDto.getCantidad() = " + ventaDto.getCantidad());

        // Buscar el producto y usuario en las bases de datos
        ProductoEntity producto = productoRepository.findById(ventaDto.getIdProducto())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        System.out.println("producto Encontrado = " + producto.toString());
        UserEntity usuario = userRepository.findByUsername(ventaDto.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        System.out.println("usuario Encontrado = " + usuario.toString());

        // Crear una entidad de venta
        VentaEntity venta = new VentaEntity();
        venta.setProducto(producto);
        venta.setUsuario(usuario);
        venta.setCantidad(ventaDto.getCantidad());
        venta.setFechaVenta(new Date());

        // Guardar la venta
        VentaEntity ventaGuardada = ventaRepository.save(venta);

        // Mapear la entidad guardada al DTO
        return new VentaEntityDto(
                ventaGuardada.getProducto().getId(),
                ventaGuardada.getProducto().getPrecio(),
                ventaGuardada.getUsuario().getUsername(),
                ventaGuardada.getCantidad()
        );
    }

    @Override
    public VentaEntity registrarVenta(VentaEntity venta) {
        return null;
    }
}
