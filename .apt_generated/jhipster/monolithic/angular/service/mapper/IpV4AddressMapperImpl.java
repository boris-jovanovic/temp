package jhipster.monolithic.angular.service.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import jhipster.monolithic.angular.domain.IpPool;
import jhipster.monolithic.angular.domain.IpV4Address;
import jhipster.monolithic.angular.domain.VLan;
import jhipster.monolithic.angular.service.dto.IpV4AddressDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2017-10-04T14:33:00+0200",
    comments = "version: 1.1.0.Final, compiler: Eclipse JDT (IDE) 3.13.0.v20170516-1929, environment: Java 1.8.0_144 (Oracle Corporation)"
)
@Component
public class IpV4AddressMapperImpl implements IpV4AddressMapper {

    @Autowired
    private VLanMapper vLanMapper;
    @Autowired
    private PoolMapper poolMapper;

    @Override
    public List<IpV4Address> toEntity(List<IpV4AddressDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<IpV4Address> list = new ArrayList<IpV4Address>();
        for ( IpV4AddressDTO ipV4AddressDTO : dtoList ) {
            list.add( toEntity( ipV4AddressDTO ) );
        }

        return list;
    }

    @Override
    public List<IpV4AddressDTO> toDto(List<IpV4Address> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<IpV4AddressDTO> list = new ArrayList<IpV4AddressDTO>();
        for ( IpV4Address ipV4Address : entityList ) {
            list.add( toDto( ipV4Address ) );
        }

        return list;
    }

    @Override
    public IpV4AddressDTO toDto(IpV4Address ipV4Address) {
        if ( ipV4Address == null ) {
            return null;
        }

        IpV4AddressDTO ipV4AddressDTO_ = new IpV4AddressDTO();

        ipV4AddressDTO_.setPoolId( ipV4AddressPoolId( ipV4Address ) );
        ipV4AddressDTO_.setVLanId( ipV4AddressVLanId( ipV4Address ) );
        ipV4AddressDTO_.setId( ipV4Address.getId() );
        ipV4AddressDTO_.setIpV4Address( ipV4Address.getIpV4Address() );

        return ipV4AddressDTO_;
    }

    @Override
    public IpV4Address toEntity(IpV4AddressDTO ipV4AddressDTO) {
        if ( ipV4AddressDTO == null ) {
            return null;
        }

        IpV4Address ipV4Address_ = new IpV4Address();

        ipV4Address_.setPool( poolMapper.fromId( ipV4AddressDTO.getPoolId() ) );
        ipV4Address_.setVLan( vLanMapper.fromId( ipV4AddressDTO.getVLanId() ) );
        ipV4Address_.setId( ipV4AddressDTO.getId() );
        ipV4Address_.setIpV4Address( ipV4AddressDTO.getIpV4Address() );

        return ipV4Address_;
    }

    private Long ipV4AddressPoolId(IpV4Address ipV4Address) {

        if ( ipV4Address == null ) {
            return null;
        }
        IpPool pool = ipV4Address.getPool();
        if ( pool == null ) {
            return null;
        }
        Long id = pool.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long ipV4AddressVLanId(IpV4Address ipV4Address) {

        if ( ipV4Address == null ) {
            return null;
        }
        VLan vLan = ipV4Address.getVLan();
        if ( vLan == null ) {
            return null;
        }
        Long id = vLan.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
