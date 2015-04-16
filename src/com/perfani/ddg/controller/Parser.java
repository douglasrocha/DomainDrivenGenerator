package com.perfani.ddg.controller;

import java.util.ArrayList;
import java.util.List;

import com.perfani.ddg.exceptions.EntityNotFoundException;
import com.perfani.ddg.exceptions.InvalidMultiplicityException;
import com.perfani.ddg.model.Entity;
import com.perfani.ddg.model.Field;
import com.perfani.ddg.model.Relationship;
import com.perfani.ddg.utils.RegexService;

public class Parser
{
    private final String entityPattern = "(\\s*)[A-Za-z0-9_]+(\\s*)\\|((\\s*)[A-Za-z0-9_:]+(\\s*))+\\|((\\s*)[A-Za-z0-9_:]*(\\s*))*";
    private final String relationshipPattern = "\\[[A-Za-z0-9_]+\\][1*]\\-[1*]\\[[A-Za-z0-9_]+\\]";
    private final String wordPattern = "[A-Za-z0-9_:]+";
    
    /***
     * 
     * @param fileContent
     * @return
     */
    public List<Entity> GetAllEntities(String fileContent)
    {
        // Creates empty output list
        List<Entity> listEntity = new ArrayList<Entity>();
        
        // Looks for all strings that match with the entity pattern
        List<String> listEntityStr = RegexService.getMatches(fileContent, entityPattern);
        
        // Iterate through all strings that follow the entity pattern
        for (String strEntity : listEntityStr)
        {
            Entity entity = new Entity();
            ArrayList<Field> entityKeys = new ArrayList<Field>();
            ArrayList<Field> entityFields = new ArrayList<Field>();
            
            // Splits string using | character
            String[] explodedStr = strEntity.split("\\|");
            
            // Sets entity name
            entity.setName(explodedStr[0].trim());
            
            // Adds keys to entity
            List<String> listKeysStr = RegexService.getMatches(explodedStr[1], wordPattern);
            
            for (String key : listKeysStr)
            {
                // Splits key with : separator
                String[] explodedKey = key.split("[:]");
                
                Field field = new Field();
                field.setName(explodedKey[0].trim());
                field.setType(explodedKey[1].trim());
                
                entityKeys.add(field);
            }
            
            entity.setKeys(entityKeys);
            
            // Adds attributes to entity
            List<String> listAttrStr = RegexService.getMatches(explodedStr[2], wordPattern);
            
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
    public List<Relationship> GetAllRelationships(List<Entity> listEntities, String fileContent) 
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
