/***
 *    Copyright 2015 Douglas Bellon Rocha
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.perfani.ddg.action;

import java.util.ArrayList;
import java.util.List;

import com.perfani.ddg.domain.model.Entity;
import com.perfani.ddg.domain.model.Field;
import com.perfani.ddg.domain.model.Relationship;
import com.perfani.ddg.exceptions.EntityNotFoundException;
import com.perfani.ddg.exceptions.InvalidKeyAmountException;
import com.perfani.ddg.exceptions.InvalidMultiplicityException;
import com.perfani.ddg.service.RegexService;

public class Parser
{
    private final static String entityPattern = "(\\s*)[A-Za-z0-9_]+(\\s*)\\|((\\s*)[A-Za-z0-9_:]+(\\s*))+\\|((\\s*)[A-Za-z0-9_:<>]*(\\s*),{0,1})*";
    private final static String relationshipPattern = "\\[[A-Za-z0-9_]+\\][1*]\\-[1*]\\[[A-Za-z0-9_]+\\]";
    private final static String wordPattern = "[A-Za-z0-9_]+\\s*\\:\\s*[A-Za-z0-9<>]+";
    
    /***
     * 
     * @param fileContent
     * @return
     * @throws InvalidKeyAmountException 
     */
    public static List<Entity> GetAllEntities(String fileContent) throws InvalidKeyAmountException
    {
        // Creates empty output list
        List<Entity> listEntity = new ArrayList<Entity>();
        
        // Looks for all strings that match with the entity pattern
        List<String> listEntityStr = RegexService.getMatches(fileContent, entityPattern);
        
        // Iterate through all strings that follow the entity pattern
        for (String strEntity : listEntityStr)
        {
            Entity entity = new Entity();
            ArrayList<Field> entityFields = new ArrayList<Field>();
            
            // Splits string using | character
            String[] explodedStr = strEntity.split("\\|");
            
            // Sets entity name
            entity.setName(explodedStr[0].trim());
            
            // Adds keys to entity
            List<String> listKeysStr = RegexService.getMatches(explodedStr[1], wordPattern);
            
            if (listKeysStr.size() != 1) throw new InvalidKeyAmountException();
            
            String entityKey = (String) listKeysStr.toArray()[0];
            
            // Splits key with : separator
            String[] explodedKey = entityKey.split("[:]");
            
            Field key = new Field();
            key.setName(explodedKey[0].trim());
            key.setType(explodedKey[1].trim());            
            
            entity.setKey(key);
            
            // Adds attributes to entity
            String[] listAttrStr = explodedStr[2].split(",");
            
            for (String attr : listAttrStr)
            {
                String[] explodedAttr = attr.split("[:]");
                
                Field field = new Field();
                field.setName(explodedAttr[0].trim());
                field.setType(explodedAttr[1].trim());
                
                entityFields.add(field);
            }
            
            entity.setFields(entityFields);
            
            // Adds entity to output list
            listEntity.add(entity);
        }
        
        return listEntity;
    }
    
    /***
     * 
     * @param fileContent
     * @return
     * @throws InvalidMultiplicityException 
     */
    public static List<Relationship> GetAllRelationships(List<Entity> listEntities, String fileContent) 
            throws EntityNotFoundException, InvalidMultiplicityException
    {
        List<Relationship> listRelationship = new ArrayList<Relationship>();
        
        // Looks for all strings that match with the relationship pattern
        List<String> listRelStr = RegexService.getMatches(fileContent, relationshipPattern);
        
        // Iterate through all strings that follow the relationship pattern
        for (String strRel : listRelStr)
        {
            Relationship relationship = new Relationship();
            
            String[] explodedRel = strRel.split("[-]");
            
            // Gets entity and multiplicity on the left
            Entity leftEntity = null;
            String parsedLeft = explodedRel[0].trim().replace("[", "").replace("]", "");
            String[] explodedLeft = parsedLeft.split(" ");
            
            for(Entity entity : listEntities)
            {
                if (entity.getName().equals(explodedLeft[0].trim()))
                {
                    leftEntity = entity;
                }
            }
            
            if (leftEntity == null) 
            {
                throw new EntityNotFoundException();
            }
            
            if (explodedLeft[1] != "1" && explodedLeft[1] != "*")
            {
                throw new InvalidMultiplicityException();
            }

            relationship.setFrom(leftEntity);
            relationship.setnFrom(explodedLeft[1]);
            
            // Gets entity and multiplicity on the left
            Entity rightEntity = null;
            String parsedRight = explodedRel[1].trim().replace("[", "").replace("]", "");
            String[] explodedRight = parsedRight.split(" ");
            
            for(Entity entity : listEntities)
            {
                if (entity.getName().equals(explodedRight[0].trim()))
                {
                    rightEntity = entity;
                }
            }
            
            if (rightEntity == null) 
            {
                throw new EntityNotFoundException();
            }
            
            if (explodedRight[1] != "1" && explodedRight[1] != "*")
            {
                throw new InvalidMultiplicityException();
            }
            
            relationship.setTo(rightEntity);
            relationship.setnTo(explodedRight[1]);
        }
        
        return listRelationship;
    }

}
