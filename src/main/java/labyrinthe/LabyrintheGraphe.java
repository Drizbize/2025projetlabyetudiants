/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package labyrinthe;

import java.io.IOException;
import java.util.Collection;
import org.jgrapht.GraphPath;
import org.jgrapht.Graphs;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

/**
 *
 * @author rdanilchenko
 */
public class LabyrintheGraphe extends Labyrinthe {
    private final SimpleGraph<ISalle, DefaultEdge> m_graph = new SimpleGraph<>(DefaultEdge.class);
    
    public LabyrintheGraphe(String[] etagesFiles) throws IOException {
        super(etagesFiles);
        
        for (IEtage e : m_floors)
        {
            for (ISalle s : e)
            {
                m_graph.addVertex(s);
            }
        }
        
        for (IEtage e : m_floors)
        {
            for (ISalle s : e)
            {
                Collection<ISalle> sallesAccessibles = sallesAccessiblesBySalle(s);
                for (ISalle accessSalle : sallesAccessibles)
                {
                    if (!m_graph.containsEdge(s, accessSalle))
                    {
                        m_graph.addEdge(s, accessSalle);
                    }
                }
            }
        }
    }
    
    @Override
    public Collection<ISalle> chemin(ISalle u, ISalle v) {
        DijkstraShortestPath<ISalle, DefaultEdge> dsp = new DijkstraShortestPath<>(m_graph);
        GraphPath<ISalle, DefaultEdge> graphpath = dsp.getPath(u, v);
        if(graphpath == null)
        {
            System.err.println("Path from u to v not found");
            return null;
        }
        
        return graphpath.getVertexList();
    }
}
