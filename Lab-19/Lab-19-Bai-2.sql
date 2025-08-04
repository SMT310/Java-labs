USE sales_management;

-- Yêu cầu 1
SELECT id, name, origin FROM product WHERE origin = 'Trung Quoc';

-- Yêu cầu 2
SELECT id, name, unit FROM product WHERE unit IN ('cay', 'quyen');

-- Yêu cầu 3
SELECT id, name FROM product WHERE name LIKE 'B%' AND id LIKE '%01';

-- Yêu cầu 4
SELECT id, name
FROM product
WHERE
    origin = 'Trung Quoc'
    AND price BETWEEN 30000 AND 40000;

-- Yêu cầu 5
SELECT id, name
FROM product
WHERE
    origin IN ('Trung Quoc', 'Thai Lan')
    AND price BETWEEN 30000 and 40000;

-- Yêu cầu 6
SELECT id, invoice_total
FROM invoice
WHERE
    invoice_date BETWEEN '2007-01-01' AND '2007-01-02';

-- Yêu cầu 7
SELECT id, invoice_total
FROM invoice
WHERE
    invoice_date = '2007-01-01'
ORDER BY invoice_date ASC, invoice_total DESC;

-- Yêu cầu 8
SELECT DISTINCT
    c.id,
    c.full_name
FROM customer c
    JOIN invoice i ON c.id = i.customer_id
WHERE
    i.invoice_date = '2007-01-01';

-- Yêu cầu 9
SELECT i.id, i.invoice_total
FROM invoice i
    JOIN employee e ON i.employee_id = e.id
WHERE
    e.full_name = 'Nguyen Van B'
    AND i.invoice_date = '2006-01-28';

-- Yêu cầu 10
SELECT DISTINCT
    i.id
FROM invoice i
    JOIN invoice_detail id ON i.id = id.invoice_id
WHERE
    id.product_id IN ('BB01', 'BB02');

-- Yêu cầu 11
SELECT DISTINCT
    i.id
FROM invoice i
    JOIN invoice_detail id ON i.id = id.invoice_id
WHERE
    id.product_id IN ('BB01', 'BB02')
    AND id.amount BETWEEN 10 AND 20;

-- Yêu cầu 12
(
    SELECT id, invoice_total
    FROM invoice
    WHERE
        invoice_total = (
            SELECT MAX(invoice_total)
            FROM invoice
        )
)
UNION
(
    SELECT id, invoice_total
    FROM invoice
    WHERE
        invoice_total = (
            SELECT MIN(invoice_total)
            FROM invoice
        )
);

-- Yêu cầu 13
SELECT AVG(invoice_total) AS average_invoice_total
FROM invoice
WHERE
    YEAR(invoice_date) = 2006;

-- Yêu cầu 14
SELECT SUM(p.price * id.amount) AS total_profit
FROM
    invoice i
    JOIN invoice_detail id ON i.id = id.invoice_id
    JOIN product p ON id.product_id = p.id
WHERE
    YEAR(i.invoice_date) = 2006;

-- Yêu cầu 15
SELECT c.id, c.full_name, SUM(id.amount) AS total_quantity
FROM
    customer c
    JOIN invoice i ON c.id = i.customer_id
    JOIN invoice_detail id ON i.id = id.invoice_id
GROUP BY
    c.id,
    c.full_name
ORDER BY total_quantity DESC
LIMIT 3;

-- Yêu cầu 16
SELECT COUNT(*) AS product_count
FROM product
WHERE
    origin = 'Trung Quoc';

-- Yêu cầu 17
SELECT COUNT(*) AS product_count
FROM product
WHERE
    origin IN ('Trung Quoc', 'Thai Lan');

-- Yêu cầu 18
SELECT origin, COUNT(*) AS product_count
FROM product
GROUP BY
    origin;

-- Yêu cầu 19
SELECT
    origin,
    MAX(price) AS max_price,
    MIN(price) AS min_price,
    AVG(price) AS avg_price
FROM product
GROUP BY
    origin;

-- Yêu cầu 20
SELECT i.invoice_date, SUM(p.price * id.amount) AS daily_profit
FROM
    invoice i
    JOIN invoice_detail id ON i.id = id.invoice_id
    JOIN product p ON id.product_id = p.id
GROUP BY
    i.invoice_date
ORDER BY i.invoice_date;