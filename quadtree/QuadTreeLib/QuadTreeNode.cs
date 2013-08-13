﻿using System;
using System.Collections.Generic;
using System.Drawing;
using System.Diagnostics;

namespace QuadTreeLib
{
    /// <summary>
    /// The QuadTreeNode
    /// </summary>
    /// <typeparam name="T"></typeparam>
    public class QuadTreeNode<T> where T : IHasRect
    {
        /// <summary>
        /// Construct a quadtree node with the given bounds 
        /// </summary>
        /// <param name="area"></param>
        public QuadTreeNode(RectangleF bounds)
        {
            m_bounds = bounds;
        }

        /// <summary>
        /// The area of this node
        /// 这个节点占据的区域
        /// </summary>
        RectangleF m_bounds;
        /// <summary>
        /// Area of the quadtree node
        /// </summary>
        public RectangleF Bounds { get { return m_bounds; } }
        /// <summary>
        /// The contents of this node.
        /// Note that the contents have no limit: this is not the standard way to impement a QuadTree
        /// 节点内包含的项列表，项的类型为T，T 要实现 IHasRect 接口
        /// </summary>
        List<T> m_contents = new List<T>();

        /// <summary>
        /// The child nodes of the QuadTree
        /// 当前节点的子节点，肯定是4个，要不怎么叫做 quadtree 呢。
        /// </summary>
        List<QuadTreeNode<T>> m_nodes = new List<QuadTreeNode<T>>(4);

        /// <summary>
        /// Is the node empty
        /// </summary>
        public bool IsEmpty { get { return m_bounds.IsEmpty || m_nodes.Count == 0; } }

        

        /// <summary>
        /// Total number of nodes in the this node and all SubNodes
        /// </summary>
        public int Count
        {
            get
            {
                int count = 0;

                foreach (QuadTreeNode<T> node in m_nodes)
                    count += node.Count;

                count += this.Contents.Count;

                return count;
            }
        }

        /// <summary>
        /// Return the contents of this node and all subnodes in the true below this one.
        /// 全部 quadtree 包含项目，递归全部层次。
        /// </summary>
        public List<T> SubTreeContents
        {
            get
            {
                List<T> results = new List<T>();

                foreach (QuadTreeNode<T> node in m_nodes)
                    results.AddRange(node.SubTreeContents);

                results.AddRange(this.Contents);
                return results;
            }
        }
        /// <summary>
        /// 当前节点包含的全部项
        /// </summary>
        public List<T> Contents { get { return m_contents; } }

        /// <summary>
        /// Query the QuadTree for items that are in the given area
        /// 查询 quadtree，获取全部给定区域内的项
        /// </summary>
        /// <param name="queryArea"></pasram>
        /// <returns></returns>
        public List<T> Query(RectangleF queryArea)
        {
            // create a list of the items that are found
            List<T> results = new List<T>();

            // this quad contains items that are not entirely contained by
            // it's four sub-quads. Iterate through the items in this quad 
            // to see if they intersect.
            // 当前节点内 的全部项目，一个个的遍历，如果和 queryArea　相交，就放入结果集
            foreach (T item in this.Contents)
            {
                if (queryArea.IntersectsWith(item.Rectangle))
                    results.Add(item);
            }
            // 遍历 子节点
            foreach (QuadTreeNode<T> node in m_nodes)
            {
                if (node.IsEmpty)
                    continue;

                // Case 1: search area completely contained by sub-quad
                // if a node completely contains the query area, go down that branch
                // and skip the remaining nodes (break this loop)
                // 如果 node.bounds 完全包含了 queryArea，就忽略其他分支，直接到此节点下查找
                if (node.Bounds.Contains(queryArea))
                {
                    results.AddRange(node.Query(queryArea));
                    break;
                }

                // Case 2: Sub-quad completely contained by search area 
                // if the query area completely contains a sub-quad,
                // just add all the contents of that quad and it's children 
                // to the result set. You need to continue the loop to test 
                // the other quads
                // 子四角 如果完全被 queryArea 包含，那么把这个子四角 内的全部项目放到结果集
                if (queryArea.Contains(node.Bounds))
                {
                    results.AddRange(node.SubTreeContents);
                    continue;
                }

                // Case 3: search area intersects with sub-quad
                // traverse into this quad, continue the loop to search other
                // quads
                // queryArea 和 node 相交，那么继续到此 node 内查询
                if (node.Bounds.IntersectsWith(queryArea))
                {
                    results.AddRange(node.Query(queryArea));
                }
            }


            return results;
        }

        /// <summary>
        /// Insert an item to this node
        /// </summary>
        /// <param name="item"></param>
        public void Insert(T item)
        {
            // if the item is not contained in this quad, there's a problem
            if (!m_bounds.Contains(item.Rectangle))
            {
                Trace.TraceWarning("feature is out of the bounds of this quadtree node");
                return;
            }

            // if the subnodes are null create them. may not be sucessfull: see below
            // we may be at the smallest allowed size in which case the subnodes will not be created
            if (m_nodes.Count == 0)
                CreateSubNodes();

            // for each subnode:
            // if the node contains the item, add the item to that node and return
            // this recurses into the node that is just large enough to fit this item
            foreach (QuadTreeNode<T> node in m_nodes)
            {
                if (node.Bounds.Contains(item.Rectangle))
                {
                    node.Insert(item);
                    return;
                }
            }

            // if we make it to here, either
            // 1) none of the subnodes completely contained the item. or
            // 2) we're at the smallest subnode size allowed 
            // add the item to this node's contents.
            this.Contents.Add(item);
        }

        public void ForEach(QuadTree<T>.QTAction action)
        {
            action(this);

            // draw the child quads
            foreach (QuadTreeNode<T> node in this.m_nodes)
                node.ForEach(action);
        }

        /// <summary>
        /// Internal method to create the subnodes (partitions space)
        /// 每个节点 内建 4个子节点，空间划分
        /// </summary>
        private void CreateSubNodes()
        {
            // the smallest subnode has an area 
            if ((m_bounds.Height * m_bounds.Width) <= 10)
                return;

            float halfWidth = (m_bounds.Width / 2f);
            float halfHeight = (m_bounds.Height / 2f);

            m_nodes.Add(new QuadTreeNode<T>(new RectangleF(m_bounds.Location, new SizeF(halfWidth, halfHeight))));
            m_nodes.Add(new QuadTreeNode<T>(new RectangleF(new PointF(m_bounds.Left, m_bounds.Top + halfHeight), new SizeF(halfWidth, halfHeight))));
            m_nodes.Add(new QuadTreeNode<T>(new RectangleF(new PointF(m_bounds.Left + halfWidth, m_bounds.Top), new SizeF(halfWidth, halfHeight))));
            m_nodes.Add(new QuadTreeNode<T>(new RectangleF(new PointF(m_bounds.Left + halfWidth, m_bounds.Top + halfHeight), new SizeF(halfWidth, halfHeight))));
        }

    }
}