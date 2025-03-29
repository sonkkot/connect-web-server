package site.copi.security.users.repository;

import site.copi.security.users.CopiUserDetails;
import site.copi.security.users.dto.CopiOAuth2ProviderDTO;

public interface CopiOAuth2UserDetailsPort {
    CopiUserDetails load(String oAuth2Id);

    void register(CopiOAuth2ProviderDTO dto);

    boolean isNewMember(String oAuth2Id);
}