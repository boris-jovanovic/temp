package jhipster.monolithic.angular.service.mapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Generated;
import jhipster.monolithic.angular.domain.L2Domain;
import jhipster.monolithic.angular.domain.VLan;
import jhipster.monolithic.angular.domain.VLanPool;
import jhipster.monolithic.angular.service.dto.VLanDTO;
import jhipster.monolithic.angular.service.dto.VLanPoolDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2017-10-10T10:22:44+0200",
    comments = "version: 1.1.0.Final, compiler: Eclipse JDT (IDE) 3.13.0.v20170516-1929, environment: Java 1.8.0_144 (Oracle Corporation)"
)
@Component
public class VLanPoolMapperImpl implements VLanPoolMapper {

    @Autowired
    private L2DomainMapper l2DomainMapper;
    @Autowired
    private VLanMapper vLanMapper;

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

        vLanPool_.setDomain( l2DomainMapper.fromId( vLanPoolDTO.getDomainId() ) );
        Set<VLan> set = vLanDTOListToVLanSet( vLanPoolDTO.getVLans() );
        if ( set != null ) {
            vLanPool_.setVLans( set );
        }
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

        vLanPoolDTO_.setDomainId( vLanPoolDomainId( vLanPool ) );
        List<VLanDTO> list = vLanSetToVLanDTOList( vLanPool.getVLans() );
        if ( list != null ) {
            vLanPoolDTO_.setVLans( list );
        }
        vLanPoolDTO_.setId( vLanPool.getId() );
        vLanPoolDTO_.setName( vLanPool.getName() );

        return vLanPoolDTO_;
    }

    protected Set<VLan> vLanDTOListToVLanSet(List<VLanDTO> list) {
        if ( list == null ) {
            return null;
        }

        Set<VLan> set = new HashSet<VLan>();
        for ( VLanDTO vLanDTO : list ) {
            set.add( vLanMapper.toEntity( vLanDTO ) );
        }

        return set;
    }

    private Long vLanPoolDomainId(VLanPool vLanPool) {

        if ( vLanPool == null ) {
            return null;
        }
        L2Domain domain = vLanPool.getDomain();
        if ( domain == null ) {
            return null;
        }
        Long id = domain.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected List<VLanDTO> vLanSetToVLanDTOList(Set<VLan> set) {
        if ( set == null ) {
            return null;
        }

        List<VLanDTO> list = new ArrayList<VLanDTO>();
        for ( VLan vLan : set ) {
            list.add( vLanMapper.toDto( vLan ) );
        }

        return list;
    }
}
