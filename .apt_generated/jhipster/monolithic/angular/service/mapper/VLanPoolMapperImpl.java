package jhipster.monolithic.angular.service.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import jhipster.monolithic.angular.domain.VLanPool;
import jhipster.monolithic.angular.service.dto.VLanPoolDTO;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2017-10-04T14:37:09+0200",
    comments = "version: 1.1.0.Final, compiler: Eclipse JDT (IDE) 3.13.0.v20170516-1929, environment: Java 1.8.0_144 (Oracle Corporation)"
)
@Component
public class VLanPoolMapperImpl implements VLanPoolMapper {

    @Override
    public List<VLanPoolDTO> toDto(List<VLanPool> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<VLanPoolDTO> list = new ArrayList<VLanPoolDTO>();
        for ( VLanPool vLanPool : arg0 ) {
            list.add( toDto( vLanPool ) );
        }

        return list;
    }

    @Override
    public List<VLanPool> toEntity(List<VLanPoolDTO> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<VLanPool> list = new ArrayList<VLanPool>();
        for ( VLanPoolDTO vLanPoolDTO : arg0 ) {
            list.add( toEntity( vLanPoolDTO ) );
        }

        return list;
    }

    @Override
    public VLanPool toEntity(VLanPoolDTO vLanPoolDTO) {
        if ( vLanPoolDTO == null ) {
            return null;
        }

        VLanPool vLanPool_ = new VLanPool();

        vLanPool_.setId( vLanPoolDTO.getId() );
        vLanPool_.setName( vLanPoolDTO.getName() );

        return vLanPool_;
    }

    @Override
    public VLanPoolDTO toDto(VLanPool vLanPool) {
        if ( vLanPool == null ) {
            return null;
        }

        VLanPoolDTO vLanPoolDTO_ = new VLanPoolDTO();

        vLanPoolDTO_.setId( vLanPool.getId() );
        vLanPoolDTO_.setName( vLanPool.getName() );

        return vLanPoolDTO_;
    }
}
