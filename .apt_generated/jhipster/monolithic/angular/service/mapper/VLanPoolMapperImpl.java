package jhipster.monolithic.angular.service.mapper;

import java.util.ArrayList;
import java.util.List;

import jhipster.monolithic.angular.domain.VLanPool;
import jhipster.monolithic.angular.service.dto.VLanPoolDTO;

import org.springframework.stereotype.Component;

@Component
public class VLanPoolMapperImpl implements VLanPoolMapper {

    @Override
    public VLanPoolDTO toDto(VLanPool vLanPool) {
        if ( vLanPool == null ) {
            return null;
        }

        VLanPoolDTO vLanPoolDTO = new VLanPoolDTO();

        vLanPoolDTO.setId( vLanPool.getId() );
        vLanPoolDTO.setName( vLanPool.getName() );

        return vLanPoolDTO;
    }

    @Override
    public List<VLanPoolDTO> toDto(List<VLanPool> vLanPools) {
        if ( vLanPools == null ) {
            return null;
        }

        List<VLanPoolDTO> result = new ArrayList<>();
        for ( VLanPool vLanPool : vLanPools ) {
            result.add( toDto( vLanPool ) );
        }

        return result;
    }

    @Override
    public List<VLanPool> toEntity(List<VLanPoolDTO> vLanPoolDTOs) {
        if ( vLanPoolDTOs == null ) {
            return null;
        }

        List<VLanPool> result = new ArrayList<>();
        for ( VLanPoolDTO vLanPoolDTO : vLanPoolDTOs ) {
            result.add( toEntity( vLanPoolDTO ) );
        }

        return result;
    }

    @Override
    public VLanPool toEntity(VLanPoolDTO vLanPoolDTO) {
        if ( vLanPoolDTO == null ) {
            return null;
        }

        VLanPool vLanPool = new VLanPool();

        vLanPool.setId( vLanPoolDTO.getId() );
        vLanPool.setName( vLanPoolDTO.getName() );

        return vLanPool;
    }
}
