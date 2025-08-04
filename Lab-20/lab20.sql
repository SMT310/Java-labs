-- Yêu cầu 1
db.createCollection ("orders");

db.products.insertMany([
  { name: "Laptop", price: 1500, category: "Electronics" },
  { name: "Chair", price: 100, category: "Furniture" },
  { name: "Phone", price: 800, category: "Electronics" }
]);

-- Yêu cầu 2
db.createCollection ("orders");

db.orders.insertMany([
  { orderId: 1, customerName: "John Doe", orderDate: new Date("2025-08-01"), totalAmount: 2000 },
  { orderId: 2, customerName: "Jane Smith", orderDate: new Date("2025-08-02"), totalAmount: 1200 }
]);

-- Yêu cầu 3
db.createCollection ("users");

db.users.insertMany([
  { name: "Alice", email: "alice@example.com", age: 28 },
  { name: "Bob", email: "bob@example.com", age: 19 },
  { name: "Charlie", email: "charlie@example.com", age: 32 },
  { name: "David", email: "david@example.com", age: 27 },
  { name: "Eve", email: "eve@example.com", age: 22 }
]);

-- Yêu cầu 4
db.users.find(
  { age: { $gt: 25 } },
  { _id: 0, name: 1, email: 1 }
);

-- Yêu cầu 5
db.users.updateOne( { name: "Alice" }, { $set: { age: 31 } } );

-- Yêu cầu 6
db.users.deleteMany( { age: { $lt: 20 } } );

-- Yêu cầu 7
db.users.find().sort({ age: -1 }).limit(3);

-- Yêu cầu 8
db.users.find(
  {},
  { _id: 0, name: 1, age: 1 }
).sort({ age: -1 }).limit(3);

-- Yêu cầu 9
db.users.aggregate([
  { $group: { _id: "$age", count: { $sum: 1 } } }
]);

-- Yêu cầu 10
db.users.aggregate([
  { $match: { age: { $gte: 25 } } },
  { $group: { _id: null, averageAge: { $avg: "$age" } } }
]);