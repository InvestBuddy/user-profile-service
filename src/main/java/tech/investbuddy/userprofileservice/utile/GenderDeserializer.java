package tech.investbuddy.userprofileservice.utile;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import tech.investbuddy.userprofileservice.model.UserProfile;

import java.io.IOException;

public class GenderDeserializer extends JsonDeserializer<UserProfile.Gender> {
    @Override
    public UserProfile.Gender deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String value = p.getText();
        return UserProfile.Gender.fromValue(value);
    }
}
