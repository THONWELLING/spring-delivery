# Delivery API ThonDelivery

## Classes Diagram

```mermaid
   classDiagram
      class User {
        -id: UUID
        -name: String
        -email: String
        -password: String
        -registrationDate: LocalDateTime
        -collections: List<Collection>
      }
    
      class State {
        -id: UUID
        -name: String
      }
    
      class Restaurant {
        -id: UUID
        -name: String
        -deliveryRate: Double
        -kitchen: Kitchen
        -address: Address
        -registrationDate: LocalDateTime
        -updateDate: LocalDateTime
        -paymentTypes: List<PaymentMode>
        -products: List<Product>
      }
    
      class PurchaseOrder {
        -orderId: UUID
        -subtotal: Double
        -taxDelivery: Double
        -totalValue: Double
        -delliveryAddress: Address
        -status: OrderStatus
        -dateCreation: LocalDateTime
        -dateConfirmation: LocalDateTime
        -canceledAt: LocalDateTime
        -dateDelivered: LocalDateTime
        -paymentMode: PaymentMode
        -restaurant: Restaurant
        -user: User
        -items: List<OrderItem>
      }
    
      class Product {
        -id: UUID
        -name: String
        -description: String
        -price: BigDecimal
        -active: Boolean
        -restaurant: Restaurant
      }
    
      class Permission {
        -id: UUID
        -name: String
        -description: String
      }
    
      class PaymentMode {
        -id: UUID
        -description: String
      }
    
      enum OrderStatus {
        CREATED
        APROVED
        INVOICED
        SHIPPING
        DELIVERED
        CANCELED
      }
    
      class OrderItem {
        -itemId: UUID
        -unitPrice: Double
        -totalPrice: Double
        -quantity: Integer
        -observation: String
        -purchaseOrder: PurchaseOrder
        -product: Product
      }
    
      class Kitchen {
        -id: UUID
        -name: String
        -restaurants: List<Restaurant>
      }
    
      class Collection {
        -id: UUID
        -name: String
        -permissions: List<Permission>
      }
    
      class City {
        -id: UUID
        -name: String
        -State: State
      }
    
      class Address {
        -zipCode: String
        -street: String
        -number: String
        -complement: String
        -neighborhood: String
        -city: City
      }
    
    User "1" -- "Many" Collection
    Collection "Many" -- "Many" Permission
    
    State "1" *-- "Many" City
    City "1" *-- "Many" Address
    
    Kitchen "1" *-- "Many" Restaurant
    Restaurant "1" *-- "Many" Product
    Restaurant "1" -- "Many" PaymentMode
    
    User "1" -- "Many" PurchaseOrder
    PurchaseOrder "1" -- "1" PaymentMode
    PurchaseOrder "1" -- "1" Restaurant
    PurchaseOrder "1" -- "Many" OrderItem
    OrderItem "1" -- "1" Product

```