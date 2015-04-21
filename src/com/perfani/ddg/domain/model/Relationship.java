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

package com.perfani.ddg.domain.model;

public class Relationship
{
    private Entity from;
    
    private String nFrom;
    
    private Entity to;
    
    private String nTo;

    /**
     * @return the from
     */
    public Entity getFrom()
    {
        return from;
    }

    /**
     * @param from the from to set
     */
    public void setFrom(Entity from)
    {
        this.from = from;
    }

    /**
     * @return the nFrom
     */
    public String getnFrom()
    {
        return nFrom;
    }

    /**
     * @param nFrom the nFrom to set
     */
    public void setnFrom(String nFrom)
    {
        this.nFrom = nFrom;
    }

    /**
     * @return the to
     */
    public Entity getTo()
    {
        return to;
    }

    /**
     * @param to the to to set
     */
    public void setTo(Entity to)
    {
        this.to = to;
    }

    /**
     * @return the nTo
     */
    public String getnTo()
    {
        return nTo;
    }

    /**
     * @param nTo the nTo to set
     */
    public void setnTo(String nTo)
    {
        this.nTo = nTo;
    }
}
