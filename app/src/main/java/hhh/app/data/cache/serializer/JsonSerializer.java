/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package hhh.app.data.cache.serializer;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

/**
 * Class user as Serializer/Deserializer for user entities.
 */
public class JsonSerializer {

  private final Gson gson = new Gson();

  public JsonSerializer() {}

  public <U> String serialize(U userEntity) {
    Type type= new TypeToken<U>(){}.getType();
    String jsonString = gson.toJson(userEntity, type);
    return jsonString;
  }

  public <U> U deserialize(String jsonString) {
    Type type= new TypeToken<U>(){}.getType();
    U userEntity = gson.fromJson(jsonString, type);
    return userEntity;
  }
}
