package it.spiderweb.gui.customtable;

import java.awt.BorderLayout;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 * Creates a panel containing a dynamic and customable table
 *
 * @author IL GRIM
 * @param <E>
 */
public class CustomTableOP<E> extends JPanel {

    private List<E> elements;
    private Map<String, Integer> columns;
    private E element = null;

    private JTable table;
    private DefaultTableModel defaultModel;
    private JScrollPane scrollPane;

    /**
     * Creates a newly object table with a specified element E and inserts it on
     * newly panel object
     *
     * @param element
     * @throws InvocationTargetException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    public CustomTableOP(E element) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        super();
        defaultModel = new DefaultTableModel();
        table = new JTable(defaultModel);
        scrollPane = new JScrollPane(table);
        elements = new LinkedList<>();
        columns = new HashMap<>();
        this.element = element;
        build();
    }

    /**
     * Creates a newly object table with a specified element E and a specified
     * list of columns. Inserts the table into a newly panel object
     *
     * @param columns
     * @param element
     * @throws InvocationTargetException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    public CustomTableOP(Map<String, Integer> columns, E element) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        this(element);
        this.columns = columns;
        build();
    }

    /**
     * Creates a newly object table with a specified element E and a specified
     * list of rows. Inserts the table into a newly panel object
     *
     * @param elements
     * @param element
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     */
    public CustomTableOP(List<E> elements, E element) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        this(element);
        this.elements = elements;
        build();
    }

    /**
     * Creates a newly object table with a list of columns and a specified list
     * of rows. Inserts the table into a newly panel object
     *
     * @param columns
     * @param elements
     * @param element
     * @throws InvocationTargetException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    public CustomTableOP(Map<String, Integer> columns, List<E> elements, E element) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        this(columns, element);
        this.elements = elements;
        build();
    }

    /**
     * Inserts the specified element E at the end of the table. This method
     * analyzes all the "getters" of the specified element E, looking carefully
     * at their annotations, in particular their "defaultPriorityValue". This
     * means that for each getter method, the returned value will be added as
     * defined by its defaultPriorityValue. Otherwise, it will be added
     * casually.
     *
     * @see DefaultFieldValue, defaultPriorityValue()
     * @param element
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     */
    public void addElement(E element) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Vector<Object> row = new Vector<>();

        Class<? extends Object> elementClass = element.getClass();

        if (elementClass.isAnnotationPresent(JTBean.class)) {
            JTBean clsAnnotation = elementClass.getAnnotation(JTBean.class);

            if (clsAnnotation.active()) {
                Method[] methods = elementClass.getMethods(); //prendo tutti i metodi
                Method[] sortedMethods = new Method[methods.length]; //creo un array che conterrà metodi ordinati per priorità
                for (Method method : methods) { //per ogni metodo
                    if (method.getName().substring(0, 3).equals("get")) { //lo analizzo se è un getter
                        if (method.isAnnotationPresent(DefaultFieldValue.class)) { //ricavo le annotazioni sul metodo
                            DefaultFieldValue annotation = method.getAnnotation(DefaultFieldValue.class);
                            if (annotation.defaultPriorityValue() > 0) { //se il valore di priorità è maggiore di 0
                                sortedMethods[annotation.defaultPriorityValue() - 1] = method; //lo inserisco nell'array dei metodi ordinati
                            } else { //altrimento lo invoco e lo inserisco nella row
                                if (method.invoke(element) == null) {
                                    DefaultFieldValue mthAnnotation = method.getAnnotation(DefaultFieldValue.class);
                                    row.addElement(mthAnnotation.defaultStringValue());
                                } else {
                                    row.addElement(method.invoke(element));
                                }
                            }
                        }
                    }
                }
                for (Method method : sortedMethods) { //una volta iterati tutti i metodi, itero l'array dei metodi ordinati
                    if (method == null) { //se il metodo è nullo, esco (sono finiti i metodi)
                        break;
                    }
                    if (method.isAnnotationPresent(DefaultFieldValue.class)) { //se è presenta qualche altra annotazione la gestisco
                        if (method.invoke(element) == null) {
                            DefaultFieldValue mthAnnotation = method.getAnnotation(DefaultFieldValue.class);
                            row.addElement(mthAnnotation.defaultStringValue());
                        } else {
                            row.addElement(method.invoke(element));
                        }
                    } else {
                        row.addElement(method.invoke(element)); //altrimenti lo inserisco direttamente
                    }
                }
                this.defaultModel.addRow(row);
            }
        }
    }

    /**
     * Inserts a List of elements E at the end of the table
     *
     * @param elements
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     */
    public void addElements(List<E> elements) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        this.elements = elements;
        this.addElements();
    }

    /**
     * Inserts a specified column with a given name and a specified size
     *
     * @param name
     * @param size
     */
    public void addColumn(String name, int size) {
        this.columns.put(name, size);
        this.defaultModel.addColumn(name);
    }

    /**
     * Inserts a specified Map<String, Integer> of columns
     *
     * @param columns
     */
    public void addColumns(Map<String, Integer> columns) {
        for (Map.Entry<String, Integer> column : columns.entrySet()) {
            this.addColumn(column.getKey(), column.getValue());
        }
    }

    /**
     * Generates the rows of this table
     *
     * @throws InvocationTargetException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @see build()
     */
    protected void addElements() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        if (this.elements != null) {
            for (E elem : this.elements) {
                this.addElement(elem);
            }
        }
    }

    /**
     * Generates the columns of this table
     *
     * @see build()
     */
    protected void generateColumns() {
        Class<? extends Object> elementClass = this.element.getClass();

        if (elementClass.isAnnotationPresent(JTBean.class)) {
            Field[] f = this.element.getClass().getDeclaredFields();

            if (this.columns.isEmpty()) {
                for (Field field : f) {
                    if (field.isAnnotationPresent(FieldProperties.class)) {
                        FieldProperties annotation = field.getAnnotation(FieldProperties.class);

                        if (annotation.visible()) {
                            this.addColumn(annotation.name(), annotation.size());
                        }
                    }
                }
            } else {
                for (Map.Entry<String, Integer> column : this.columns.entrySet()) {
                    this.addColumn(column.getKey(), column.getValue());
                }
            }
        }
    }

    /**
     * Sizes the current columns of this table
     *
     * @see build
     */
    protected void sizeColumns() {
        TableColumnModel columnModel = this.table.getColumnModel();

        int columnIndex = 0;

        for (Map.Entry<String, Integer> column : this.columns.entrySet()) {
            columnModel.getColumn(columnIndex).setPreferredWidth(column.getValue());
            columnIndex++;
        }
    }

    /**
     * Returns the current element of this table
     *
     * @return the elements selected
     */
    public E getElement() {
        return element;
    }

    /**
     * Returns the rows of this table
     *
     * @return a List of elements which represents the raws of the table
     */
    public List<E> getElements() {
        return elements;
    }

    /**
     * Returns the columns of this table
     *
     * @return the a Map of elements which represents the columns of the table
     */
    public Map<String, Integer> getColumns() {
        return this.columns;
    }

    /**
     * Removes all the elements of this table
     */
    public void removeRows() {
        while(defaultModel.getRowCount()>0){
            defaultModel.removeRow(0);
        }
        elements.clear();
        columns.clear();
    }

    /**
     * Creates the table and adds it to this object panel
     *
     * @throws InvocationTargetException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @see generateColumns(), sizeColumns(), addElements()
     */
    protected final void build() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        this.setLayout(new BorderLayout());
        this.add(scrollPane, "Center");
        generateColumns();
        sizeColumns();
        addElements();
    }
}
